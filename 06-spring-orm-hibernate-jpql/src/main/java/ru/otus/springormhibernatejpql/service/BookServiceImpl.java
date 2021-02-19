package ru.otus.springormhibernatejpql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springormhibernatejpql.domain.Author;
import ru.otus.springormhibernatejpql.domain.Book;
import ru.otus.springormhibernatejpql.domain.Genre;
import ru.otus.springormhibernatejpql.dto.BookDto;
import ru.otus.springormhibernatejpql.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Book not found"));
    }

    @Transactional
    @Override
    public void save(BookDto bookDto) {
        repository.save(Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .genre(new Genre(bookDto.getGenreId()))
                .authorList(bookDto.getAuthorIds().stream().map(Author::new).collect(Collectors.toList()))
                .build());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}

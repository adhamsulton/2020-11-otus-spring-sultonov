package ru.otus.homework6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.domain.Author;
import ru.otus.homework6.domain.Book;
import ru.otus.homework6.domain.Genre;
import ru.otus.homework6.dto.BookDto;
import ru.otus.homework6.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Book findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Book not found"));
    }

    @Override
    public void save(BookDto bookDto) {
        repository.save(Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .genre(new Genre(bookDto.getGenreId()))
                .authorList(bookDto.getAuthorIds().stream().map(Author::new).collect(Collectors.toList()))
                .build());
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}

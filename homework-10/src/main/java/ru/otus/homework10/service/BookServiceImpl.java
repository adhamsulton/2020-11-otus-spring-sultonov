package ru.otus.homework10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework10.domain.Author;
import ru.otus.homework10.domain.Book;
import ru.otus.homework10.domain.Genre;
import ru.otus.homework10.dto.BookDto;
import ru.otus.homework10.repository.BookRepository;

import java.util.List;
import java.util.Optional;
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
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Book save(BookDto bookDto) {
        return repository.save(Book.builder()
                .id(bookDto.getId())
                .name(bookDto.getName())
                .genre(new Genre(bookDto.getGenreId()))
                .authorList(bookDto.getAuthorIds().stream().map(Author::new).collect(Collectors.toList()))
                .build());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

package ru.otus.homework10.service;

import ru.otus.homework10.domain.Book;
import ru.otus.homework10.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(BookDto bookDto);

    void delete(Long id);
}

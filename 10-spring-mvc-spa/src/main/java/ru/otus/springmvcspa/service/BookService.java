package ru.otus.springmvcspa.service;

import ru.otus.springmvcspa.domain.Book;
import ru.otus.springmvcspa.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(BookDto bookDto);

    void delete(Long id);
}

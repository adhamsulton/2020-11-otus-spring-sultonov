package ru.otus.homework10.service;

import ru.otus.homework10.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    void save(Book book);

    void delete(Long id);
}

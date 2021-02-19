package ru.otus.springormhibernatejpql.repository;

import ru.otus.springormhibernatejpql.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);

    void delete(Long id);
}

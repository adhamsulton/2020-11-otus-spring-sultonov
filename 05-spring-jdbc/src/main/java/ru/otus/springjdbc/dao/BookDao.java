package ru.otus.springjdbc.dao;

import ru.otus.springjdbc.domain.Book;
import ru.otus.springjdbc.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    void create(BookDto book);

    void update(BookDto book);

    void delete(Long bookId);
}

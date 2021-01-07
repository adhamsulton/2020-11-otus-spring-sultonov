package ru.otus.homework5.dao;

import ru.otus.homework5.domain.Book;
import ru.otus.homework5.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    void create(BookDto book);

    void update(BookDto book);

    void delete(Long bookId);
}

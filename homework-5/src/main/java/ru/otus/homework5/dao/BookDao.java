package ru.otus.homework5.dao;

import ru.otus.homework5.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAll();

    Book findById(Long id);

    void create(Book book);

    void update(Book book);

    void delete(Long bookId);
}

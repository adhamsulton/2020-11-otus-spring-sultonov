package ru.otus.homework8.service;

import ru.otus.homework8.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(String id) throws Exception;

    void save(Book book);

    void delete(String id);
}

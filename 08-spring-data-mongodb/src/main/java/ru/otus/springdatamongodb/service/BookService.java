package ru.otus.springdatamongodb.service;

import ru.otus.springdatamongodb.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(String id) throws Exception;

    void save(Book book);

    void delete(String id);
}

package ru.otus.springdatajpa.service;

import ru.otus.springdatajpa.domain.Book;
import ru.otus.springdatajpa.dto.BookDto;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id) throws Exception;

    void save(BookDto bookDto);

    void delete(Long id);
}

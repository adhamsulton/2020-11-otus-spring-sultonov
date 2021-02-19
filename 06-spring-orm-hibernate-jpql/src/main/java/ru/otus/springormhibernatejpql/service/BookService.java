package ru.otus.springormhibernatejpql.service;

import ru.otus.springormhibernatejpql.domain.Book;
import ru.otus.springormhibernatejpql.dto.BookDto;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id) throws Exception;

    void save(BookDto bookDto);

    void delete(Long id);
}

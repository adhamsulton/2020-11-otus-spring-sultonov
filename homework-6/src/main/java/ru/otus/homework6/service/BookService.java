package ru.otus.homework6.service;

import ru.otus.homework6.domain.Book;
import ru.otus.homework6.dto.BookDto;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id) throws Exception;

    void save(BookDto bookDto);

    void delete(Long id);
}

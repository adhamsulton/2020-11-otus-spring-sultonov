package ru.otus.homework7.service;

import ru.otus.homework7.domain.Book;
import ru.otus.homework7.dto.BookDto;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id) throws Exception;

    void save(BookDto bookDto);

    void delete(Long id);
}

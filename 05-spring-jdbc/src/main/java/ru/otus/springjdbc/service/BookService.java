package ru.otus.springjdbc.service;

import ru.otus.springjdbc.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id) throws Exception;

    void create(String name, Long genreId, List<Long> authorIdList);

    void update(Long id, String name, Long genreId, List<Long> authorIdList);

    void delete(Long bookId);
}

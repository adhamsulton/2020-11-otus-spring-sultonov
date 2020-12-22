package ru.otus.homework5.service;

import ru.otus.homework5.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id);

    void create(String name, Long genreId, List<Long> authorIdList);

    void update(Long id, String name, Long genreId, List<Long> authorIdList);

    void delete(Long bookId);
}

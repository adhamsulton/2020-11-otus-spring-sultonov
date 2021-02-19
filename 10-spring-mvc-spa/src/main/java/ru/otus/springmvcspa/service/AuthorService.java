package ru.otus.springmvcspa.service;

import ru.otus.springmvcspa.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}

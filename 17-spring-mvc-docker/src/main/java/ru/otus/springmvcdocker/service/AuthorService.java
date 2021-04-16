package ru.otus.springmvcdocker.service;

import ru.otus.springmvcdocker.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}

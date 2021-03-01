package ru.otus.springsecurity.service;

import ru.otus.springsecurity.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}

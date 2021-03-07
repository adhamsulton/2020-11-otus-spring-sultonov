package ru.otus.springsecurityacl.service;

import ru.otus.springsecurityacl.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}

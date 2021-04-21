package ru.otus.springhystrix.service;

import ru.otus.springhystrix.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}

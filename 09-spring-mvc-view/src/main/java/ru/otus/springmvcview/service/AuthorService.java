package ru.otus.springmvcview.service;

import ru.otus.springmvcview.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}

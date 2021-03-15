package ru.otus.springsecurityacl.service;

import ru.otus.springsecurityacl.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}

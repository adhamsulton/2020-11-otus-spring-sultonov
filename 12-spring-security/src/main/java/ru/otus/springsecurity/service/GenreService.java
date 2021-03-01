package ru.otus.springsecurity.service;

import ru.otus.springsecurity.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}

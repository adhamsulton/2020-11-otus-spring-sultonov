package ru.otus.springmvcspa.service;

import ru.otus.springmvcspa.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}

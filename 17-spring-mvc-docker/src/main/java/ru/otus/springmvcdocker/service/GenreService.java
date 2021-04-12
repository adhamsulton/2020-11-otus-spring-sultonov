package ru.otus.springmvcdocker.service;

import ru.otus.springmvcdocker.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}

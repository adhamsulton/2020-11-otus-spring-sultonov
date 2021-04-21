package ru.otus.springhystrix.service;

import ru.otus.springhystrix.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}

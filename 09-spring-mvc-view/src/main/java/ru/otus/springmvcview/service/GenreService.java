package ru.otus.springmvcview.service;

import ru.otus.springmvcview.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}

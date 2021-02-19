package ru.otus.springmvcspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.springmvcspa.domain.Genre;
import ru.otus.springmvcspa.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genres")
    public List<Genre> fetchGenres() {
        return genreService.findAll();
    }
}

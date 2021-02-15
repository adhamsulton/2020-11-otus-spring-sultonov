package ru.otus.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework10.domain.Genre;
import ru.otus.homework10.service.GenreService;

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

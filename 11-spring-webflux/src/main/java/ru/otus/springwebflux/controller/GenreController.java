package ru.otus.springwebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.springwebflux.domain.Genre;
import ru.otus.springwebflux.repository.GenreRepository;

@RequiredArgsConstructor
@RestController
public class GenreController {
    private final GenreRepository repository;

    @GetMapping("/genres")
    public Flux<Genre> fetch() {
        return repository.findAll();
    }

}

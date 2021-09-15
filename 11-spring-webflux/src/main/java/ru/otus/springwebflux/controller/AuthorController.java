package ru.otus.springwebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.springwebflux.domain.Author;
import ru.otus.springwebflux.repository.AuthorRepository;

@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorRepository repository;

    @GetMapping("/authors")
    public Flux<Author> fetch() {
        return repository.findAll();
    }

}

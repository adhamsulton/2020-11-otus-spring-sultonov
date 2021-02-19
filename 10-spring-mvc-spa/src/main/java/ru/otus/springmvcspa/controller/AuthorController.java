package ru.otus.springmvcspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.springmvcspa.domain.Author;
import ru.otus.springmvcspa.service.AuthorService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> fetchAuthors() {
        return authorService.findAll();
    }
}

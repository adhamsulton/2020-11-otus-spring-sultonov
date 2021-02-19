package ru.otus.springwebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.springwebflux.domain.Book;
import ru.otus.springwebflux.repository.BookRepository;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookRepository repository;

    @GetMapping("/books")
    public Flux<Book> fetch() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Mono<Book> get(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping("/books")
    public Mono<Book> save(@RequestBody Book book) {
        return repository.save(book);
    }

    @DeleteMapping("/books/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return repository.deleteById(id);
    }
}

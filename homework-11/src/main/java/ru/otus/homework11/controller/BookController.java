package ru.otus.homework11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework11.domain.Book;
import ru.otus.homework11.repository.BookRepository;

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
    public void delete(@PathVariable String id) {
        repository.deleteById(id).subscribe();
    }
}

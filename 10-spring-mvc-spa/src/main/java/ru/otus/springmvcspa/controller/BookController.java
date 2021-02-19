package ru.otus.springmvcspa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.springmvcspa.domain.Book;
import ru.otus.springmvcspa.dto.BookDto;
import ru.otus.springmvcspa.service.BookService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> fetch() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book get(@PathVariable("id") Long id) {
        return bookService.findById(id).orElse(new Book());
    }

    @PostMapping("/books")
    public Book save(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }
}

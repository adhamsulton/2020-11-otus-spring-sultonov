package ru.otus.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework10.domain.Author;
import ru.otus.homework10.domain.Book;
import ru.otus.homework10.domain.Genre;
import ru.otus.homework10.dto.BookDto;
import ru.otus.homework10.service.AuthorService;
import ru.otus.homework10.service.BookService;
import ru.otus.homework10.service.CommentService;
import ru.otus.homework10.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
@CrossOrigin(origins = {"http://localhost:4200"})
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentService commentService;

    @GetMapping
    public List<Book> fetch() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable("id") Long id) {
        return bookService.findById(id).orElse(new Book());
    }

    @PostMapping("/save")
    public Book save(@RequestBody BookDto bookDto) {
        System.out.println(bookDto);
        return bookService.save(bookDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

    @GetMapping("/genres")
    public List<Genre> fetchGenres() {
        return genreService.findAll();
    }

    @GetMapping("/authors")
    public List<Author> fetchAuthors() {
        return authorService.findAll();
    }
}

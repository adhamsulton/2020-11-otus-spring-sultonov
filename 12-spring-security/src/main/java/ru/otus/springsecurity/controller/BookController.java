package ru.otus.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.springsecurity.domain.Book;
import ru.otus.springsecurity.service.AuthorService;
import ru.otus.springsecurity.service.BookService;
import ru.otus.springsecurity.service.CommentService;
import ru.otus.springsecurity.service.GenreService;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentService commentService;

    @GetMapping("/books")
    public String getList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/books/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
        model.addAttribute("comments", commentService.findAllBookComments(id));
        return "book-view";
    }

    @GetMapping("/books/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "book-add-edit";
    }

    @PostMapping("/books")
    public String save(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/books/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}

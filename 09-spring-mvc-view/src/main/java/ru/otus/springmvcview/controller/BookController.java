package ru.otus.springmvcview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.springmvcview.domain.Book;
import ru.otus.springmvcview.service.AuthorService;
import ru.otus.springmvcview.service.BookService;
import ru.otus.springmvcview.service.CommentService;
import ru.otus.springmvcview.service.GenreService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentService commentService;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
        model.addAttribute("comments", commentService.findAllBookComments(id));
        return "book-view";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "book-add-edit";
    }

    @PostMapping("/save")
    public String save(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}

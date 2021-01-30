package ru.otus.homework9.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.homework9.domain.Book;
import ru.otus.homework9.service.AuthorService;
import ru.otus.homework9.service.BookService;
import ru.otus.homework9.service.CommentService;
import ru.otus.homework9.service.GenreService;

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
        return "bookList";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
        model.addAttribute("comments", commentService.findAllBookComments(id));
        return "view";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "addEdit";
    }

    @PostMapping("/edit")
    public String edit(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}

package ru.otus.springsecurityacl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.springsecurityacl.domain.Book;
import ru.otus.springsecurityacl.service.*;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentService commentService;
    private final UserService userService;

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

    @Secured({"ROLE_ADMIN", "ROLE_OWNER"})
    @GetMapping("/books/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("authors", authorService.findAll());
        return "book-add-edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_OWNER"})
    @PostMapping("/books")
    public String save(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @Secured({"ROLE_ADMIN", "ROLE_OWNER"})
    @DeleteMapping("/books/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}

package ru.otus.homework10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.homework10.domain.Book;
import ru.otus.homework10.dto.BookViewDto;
import ru.otus.homework10.service.AuthorService;
import ru.otus.homework10.service.BookService;
import ru.otus.homework10.service.CommentService;
import ru.otus.homework10.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentService commentService;

    @GetMapping
    public List<Book> getList() {
        return bookService.findAll();
    }

//    @GetMapping("/view/{id}")
//    public BookViewDto view(@PathVariable("id") Long id, Model model) {
//        return BookViewDto.builder()..build()("book", bookService.findById(id).orElse(new Book()));
//        model.addAttribute("comments", commentService.findAllBookComments(id));
//        return "book-view";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editPage(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("book", bookService.findById(id).orElse(new Book()));
//        model.addAttribute("genres", genreService.findAll());
//        model.addAttribute("authors", authorService.findAll());
//        return "book-add-edit";
//    }
//
//    @PostMapping("/save")
//    public String save(Book book) {
//        bookService.save(book);
//        return "redirect:/books";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        bookService.delete(id);
//        return "redirect:/books";
//    }
}

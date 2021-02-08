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

//    private List<BookObj> list = new ArrayList<>(){{add(new BookObj(0, "namenamename", new Genre(1L, "detectiv")));}};
//    private Long id = 1L;
//    @GetMapping
//    public List<BookObj> fetch() {
//        System.out.println(list);
//        return list;
//    }
//
//    @GetMapping("/{id}")
//    public BookObj get(@PathVariable("id") Long id) {
//         return list.get(Math.toIntExact(id));
//    }
//
//    @PostMapping("/save")
//    public BookObj save(@RequestBody BookObj book) {
//        System.out.println("book");
//        System.out.println(book);
//        if (book.getId() == null){
//            book.setId(Math.toIntExact(id++));
//            list.add(book);
//        } else {
//            BookObj bookObj = list.get(book.getId());
//            bookObj.setName(book.getName());
//
//        }
//        return book;
//    }
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") Long id) {
//        list.remove(Math.toIntExact(id));
//    }
//

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

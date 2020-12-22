package ru.otus.homework5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework5.domain.Book;
import ru.otus.homework5.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final BookService bookService;

    @ShellMethod(value = "Find all books command", key = {"f", "ls"})
    public String findAllBooks() {
        List<Book> bookList = bookService.findAll();
        return bookList.toString();
    }

    @ShellMethod(value = "Find book by Id command", key = {"fi", "fbi"})
    public String findBookById(@ShellOption Long id) {
        Book book = bookService.findById(id);
        return book.toString();
    }

    @ShellMethod(value = "Create new book command", key = {"c", "create"})
    public String createBook(@ShellOption String name, Long genreId, String authorIds) {
        List<Long> authorIdList = Arrays.stream(authorIds.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        bookService.create(name, genreId, authorIdList);
        return "Book created";
    }

    @ShellMethod(value = "Update book command", key = {"u", "update"})
    public String editBook(@ShellOption Long id, String name, Long genreId, String authorIds) {
        List<Long> authorIdList = Arrays.stream(authorIds.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        bookService.update(id, name, genreId, authorIdList);
        return "Book updated";
    }

    @ShellMethod(value = "Delete book command", key = {"d", "del", "delete"})
    public String deleteBook(@ShellOption Long bookId) {
        bookService.delete(bookId);
        return "Book deleted";
    }

}

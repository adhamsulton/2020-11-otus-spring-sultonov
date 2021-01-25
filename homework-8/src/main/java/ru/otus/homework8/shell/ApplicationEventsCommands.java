package ru.otus.homework8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework8.domain.Author;
import ru.otus.homework8.domain.Book;
import ru.otus.homework8.domain.Genre;
import ru.otus.homework8.service.BookService;
import ru.otus.homework8.service.CommentService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final BookService bookService;
    private final CommentService commentService;

    @ShellMethod(value = "Find all books command", key = {"f", "ls"})
    public String findAllBooks() {

        List<Book> bookList = bookService.findAll();
        return bookList.toString();
    }

    @ShellMethod(value = "Find book by Id command", key = {"fi", "fbi"})
    public String findBookById(@ShellOption String id) throws Exception {
        Book book = bookService.findById(id);
        return book.toString();
    }

    @ShellMethod(value = "Create new book command", key = {"c", "create"})
    public String createBook(@ShellOption String name, String genreId, String genreName, String authors) {
        List<Author> authorNameList = Arrays.stream(authors.split(","))
                .map(String::trim)
                .map(x -> {
                    String[] split = x.split(":");
                    return Author.builder().id(split[0]).fullName(split[1]).build();
                })
                .collect(Collectors.toList());

        bookService.save(Book.builder()
                .name(name)
                .genre(Genre.builder().id(genreId).name(genreName).build())
                .authorList(authorNameList)
                .build());
        return "Book created";
    }

    @ShellMethod(value = "Update book command", key = {"u", "update"})
    public String editBook(@ShellOption String id, String name, String genreId, String genreName, String authors) {
        List<Author> authorNameList = Arrays.stream(authors.split(","))
                .map(String::trim)
                .map(x -> {
                    String[] split = x.split(":");
                    return Author.builder().id(split[0]).fullName(split[1]).build();
                })
                .collect(Collectors.toList());

        bookService.save(Book.builder()
                .id(id)
                .name(name)
                .genre(Genre.builder().id(genreId).name(genreName).build())
                .authorList(authorNameList)
                .build());
        return "Book updated";
    }

    @ShellMethod(value = "Delete book command", key = {"d", "del", "delete"})
    public String deleteBook(@ShellOption String bookId) {
        bookService.delete(bookId);
        return "Book deleted";
    }

    @ShellMethod(value = "Find all book comments command", key = {"fc", "find-all-comment"})
    public String findAllBookComment(@ShellOption String bookId) {
        return commentService.findAllBookComments(bookId).toString();
    }

    @ShellMethod(value = "Create new comment command", key = {"cc", "create-comment"})
    public String createComment(@ShellOption String bookId, String text) throws Exception {
        commentService.create(bookId, text);
        return "Comment created";
    }

    @ShellMethod(value = "Edit comment command", key = {"uc", "update-comment"})
    public String updateComment(@ShellOption String commentId, String text) throws Exception {
        commentService.update(commentId, text);
        return "Comment updated";
    }

    @ShellMethod(value = "Delete comment command", key = {"dc", "del-comment", "delete-comment"})
    public String deleteComment(@ShellOption String commentId) {
        commentService.delete(commentId);
        return "Comment deleted";
    }

}

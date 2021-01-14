package ru.otus.homework7.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework7.domain.Book;
import ru.otus.homework7.dto.BookDto;
import ru.otus.homework7.dto.CommentDto;
import ru.otus.homework7.service.BookService;
import ru.otus.homework7.service.CommentService;

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
    public String findBookById(@ShellOption Long id) throws Exception {
        Book book = bookService.findById(id);
        return book.toString();
    }

    @ShellMethod(value = "Create new book command", key = {"c", "create"})
    public String createBook(@ShellOption String name, Long genreId, String authorIds) {
        List<Long> authorIdList = Arrays.stream(authorIds.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        bookService.save(BookDto.builder()
                .name(name)
                .genreId(genreId)
                .authorIds(authorIdList)
                .build());
        return "Book created";
    }

    @ShellMethod(value = "Update book command", key = {"u", "update"})
    public String editBook(@ShellOption Long id, String name, Long genreId, String authorIds) {
        List<Long> authorIdList = Arrays.stream(authorIds.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        bookService.save(BookDto.builder()
                .id(id)
                .name(name)
                .genreId(genreId)
                .authorIds(authorIdList)
                .build());
        return "Book updated";
    }

    @ShellMethod(value = "Delete book command", key = {"d", "del", "delete"})
    public String deleteBook(@ShellOption Long bookId) {
        bookService.delete(bookId);
        return "Book deleted";
    }

    @ShellMethod(value = "Find all book comments command", key = {"fc", "find-all-comment"})
    public String findAllBookComment(@ShellOption Long bookId) {
        return commentService.findAllBookComments(bookId).toString();
    }

    @ShellMethod(value = "Create new comment command", key = {"cc", "create-comment"})
    public String createComment(@ShellOption Long bookId, String text) throws Exception {
        commentService.create(CommentDto.builder()
                .text(text)
                .bookId(bookId)
                .build());
        return "Comment created";
    }

    @ShellMethod(value = "Edit comment command", key = {"uc", "update-comment"})
    public String updateComment(@ShellOption Long commentId, String text) throws Exception {
        commentService.update(CommentDto.builder()
                .id(commentId)
                .text(text)
                .build());
        return "Comment updated";
    }

    @ShellMethod(value = "Delete comment command", key = {"dc", "del-comment", "delete-comment"})
    public String deleteComment(@ShellOption Long commentId) {
        commentService.delete(commentId);
        return "Comment deleted";
    }

}

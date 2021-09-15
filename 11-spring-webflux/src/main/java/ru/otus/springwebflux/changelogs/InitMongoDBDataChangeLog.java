package ru.otus.springwebflux.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.springwebflux.domain.Author;
import ru.otus.springwebflux.domain.Book;
import ru.otus.springwebflux.domain.Comment;
import ru.otus.springwebflux.domain.Genre;
import ru.otus.springwebflux.repository.BookRepository;
import ru.otus.springwebflux.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {
    private Book book = new Book("1", "new book", List.of(new Author("2", "Стивен Кинг")), new Genre("2", "Детектив"));

    @ChangeSet(order = "001", id = "initBook", author = "admin", runAlways = true)
    public void initBook(BookRepository repository) {
        repository.save(book).block();
    }

    @ChangeSet(order = "002", id = "initComment", author = "admin", runAlways = true)
    public void initComments(CommentRepository repository) {
        Comment expectedComment = Comment.builder().id("1").text("new comment").createdOn(LocalDateTime.now().withNano(0)).book(book).build();
        repository.save(expectedComment).block();
    }

}

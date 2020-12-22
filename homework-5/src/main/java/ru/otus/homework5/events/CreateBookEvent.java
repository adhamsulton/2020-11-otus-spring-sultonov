package ru.otus.homework5.events;

import org.springframework.context.ApplicationEvent;
import ru.otus.homework5.dao.BookDao;
import ru.otus.homework5.domain.Book;

public class CreateBookEvent extends ApplicationEvent {
    private final BookDao bookDao;

    public CreateBookEvent(TestEventsPublisher source, BookDao bookDao, Book book) {
        super(source);
        this.bookDao = bookDao;
        bookDao.create(book);
    }
}

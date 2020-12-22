package ru.otus.homework5.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.homework5.dao.BookDao;
import ru.otus.homework5.domain.Book;

@Service
@RequiredArgsConstructor
public class TestEventsPublisher implements EventsPublisher {
    private final ApplicationEventPublisher publisher;
    private final BookDao bookDao;

    @Override
    public void publish(Book book) {
        publisher.publishEvent(new CreateBookEvent(this, bookDao, book));
    }
}

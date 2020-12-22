package ru.otus.homework5.events;


import ru.otus.homework5.domain.Book;

public interface EventsPublisher {
    void publish(Book book);
}

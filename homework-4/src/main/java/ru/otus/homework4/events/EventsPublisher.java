package ru.otus.homework4.events;

import ru.otus.homework4.domain.User;

public interface EventsPublisher {
    void publish(User user);
}

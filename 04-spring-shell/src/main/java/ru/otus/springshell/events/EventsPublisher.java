package ru.otus.springshell.events;

import ru.otus.springshell.domain.User;

public interface EventsPublisher {
    void publish(User user);
}

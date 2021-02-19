package ru.otus.springshell.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.springshell.domain.User;
import ru.otus.springshell.service.QuestionService;

@Service
@RequiredArgsConstructor
public class TestEventsPublisher implements EventsPublisher {
    private final ApplicationEventPublisher publisher;
    private final QuestionService questionService;

    @Override
    public void publish(User user) {
        publisher.publishEvent(new StartTestEvent(this, questionService, user));
    }
}

package ru.otus.homework4.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.homework4.domain.User;
import ru.otus.homework4.service.QuestionService;

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

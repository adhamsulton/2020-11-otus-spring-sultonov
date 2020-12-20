package ru.otus.homework4.events;

import org.springframework.context.ApplicationEvent;
import ru.otus.homework4.domain.User;
import ru.otus.homework4.service.QuestionService;

public class StartTestEvent extends ApplicationEvent {
    private final QuestionService questionService;

    public StartTestEvent(TestEventsPublisher source, QuestionService questionService, User user) {
        super(source);
        this.questionService = questionService;
        this.questionService.runTest(user);
    }
}

package ru.otus.springshell.events;

import org.springframework.context.ApplicationEvent;
import ru.otus.springshell.domain.User;
import ru.otus.springshell.service.QuestionService;

public class StartTestEvent extends ApplicationEvent {
    private final QuestionService questionService;

    public StartTestEvent(TestEventsPublisher source, QuestionService questionService, User user) {
        super(source);
        this.questionService = questionService;
        this.questionService.runTest(user);
    }
}

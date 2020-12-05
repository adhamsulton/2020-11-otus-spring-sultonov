package ru.otus.homework2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework2.service.QuestionService;

@ComponentScan
@Configuration
public class Homework2Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Homework2Application.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.runTest();

    }

}

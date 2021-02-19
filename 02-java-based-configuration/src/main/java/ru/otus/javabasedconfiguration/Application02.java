package ru.otus.javabasedconfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.javabasedconfiguration.service.QuestionService;

@ComponentScan
@Configuration
public class Application02 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application02.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.runTest();

    }

}

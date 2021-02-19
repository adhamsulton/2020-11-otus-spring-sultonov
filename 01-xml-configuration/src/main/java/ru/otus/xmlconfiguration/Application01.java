package ru.otus.xmlconfiguration;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.xmlconfiguration.service.QuestionService;

public class Application01 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.printQuestions();

    }

}

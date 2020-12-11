package ru.otus.homework3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework3.config.AppProps;
import ru.otus.homework3.service.QuestionService;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Homework3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Homework3Application.class, args);
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.runTest();
    }

}

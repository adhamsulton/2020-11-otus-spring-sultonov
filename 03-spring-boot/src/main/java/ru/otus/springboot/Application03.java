package ru.otus.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springboot.config.AppProps;
import ru.otus.springboot.service.QuestionService;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Application03 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application03.class, args);
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.runTest();
    }

}

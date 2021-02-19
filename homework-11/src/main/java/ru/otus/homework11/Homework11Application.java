package ru.otus.homework11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.otus.homework11.controller")
public class Homework11Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework11Application.class, args);
    }

}

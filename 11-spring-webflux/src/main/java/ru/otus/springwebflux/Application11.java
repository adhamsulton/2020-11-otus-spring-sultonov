package ru.otus.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ru.otus.springwebflux.controller")
public class Application11 {

    public static void main(String[] args) {
        SpringApplication.run(Application11.class, args);
    }

}

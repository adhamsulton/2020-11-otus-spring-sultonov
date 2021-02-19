package ru.otus.springdatamongodb;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableMongock
@SpringBootApplication
public class Application08 {

    public static void main(String[] args) {
        SpringApplication.run(Application08.class, args);
    }

    }

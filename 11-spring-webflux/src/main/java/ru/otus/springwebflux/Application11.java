package ru.otus.springwebflux;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class Application11 {

    public static void main(String[] args) {
        SpringApplication.run(Application11.class, args);
    }

}

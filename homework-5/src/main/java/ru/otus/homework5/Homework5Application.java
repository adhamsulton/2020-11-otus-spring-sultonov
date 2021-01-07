package ru.otus.homework5;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class Homework5Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Homework5Application.class, args);
        Console.main(args);
    }

}

package ru.otus.springjdbc;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class Application05 {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application05.class, args);
        Console.main(args);
    }

}

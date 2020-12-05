package ru.otus.homework2.config;

import org.springframework.context.annotation.Bean;

import java.io.PrintStream;
import java.util.Scanner;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    PrintStream getPrintStream() {
        return System.out;
    }

    @Bean
    Scanner getScanner() {
        return new Scanner(System.in);
    }
}

package ru.otus.springshell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.springshell.service.ReadWriteService;
import ru.otus.springshell.service.ReadWriteServiceImpl;

import java.util.Scanner;

@Configuration
public class AppConfiguration {
    @Bean
    ReadWriteService readWriteService() {
        return new ReadWriteServiceImpl(System.out, new Scanner(System.in));
    }

}

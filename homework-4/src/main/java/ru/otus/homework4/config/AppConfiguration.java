package ru.otus.homework4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework4.service.ReadWriteService;
import ru.otus.homework4.service.ReadWriteServiceImpl;

import java.util.Scanner;

@Configuration
public class AppConfiguration {
    @Bean
    ReadWriteService readWriteService() {
        return new ReadWriteServiceImpl(System.out, new Scanner(System.in));
    }

}

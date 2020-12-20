package ru.otus.homework4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework4.config.AppProps;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Homework4Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Homework4Application.class, args);
    }

}

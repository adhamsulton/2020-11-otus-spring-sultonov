package ru.otus.springshell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springshell.config.AppProps;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Application04 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application04.class, args);
    }

}

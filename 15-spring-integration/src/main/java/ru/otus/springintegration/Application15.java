package ru.otus.springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springintegration.domain.Log;

import java.util.List;

@SpringBootApplication
public class Application15 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application15.class, args);

        Logger logger = context.getBean(Logger.class);
        List<Log> logs = List.of(new Log(1L, "{actionName: getBankAccount}"),
                new Log(1L, "{actionName: getClient}"));

        logs.forEach(logger::process);
    }

}

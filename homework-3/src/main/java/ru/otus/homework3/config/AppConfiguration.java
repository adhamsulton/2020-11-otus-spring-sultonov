package ru.otus.homework3.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework3.service.ReadWriteService;
import ru.otus.homework3.service.ReadWriteServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Configuration
public class AppConfiguration {
    @Bean
    ReadWriteService readWriteService() {
        return new ReadWriteServiceImpl(System.out, new Scanner(System.in));
    }

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/bundle");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        return messageSource;
    }
}

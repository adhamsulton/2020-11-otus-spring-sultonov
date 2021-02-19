package ru.otus.javabasedconfiguration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class DataSource {
    private final String fileName;

    public DataSource(@Value("${csv-filename}") String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

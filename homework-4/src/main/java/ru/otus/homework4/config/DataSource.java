package ru.otus.homework4.config;

import org.springframework.stereotype.Component;

@Component
public class DataSource {
    private final String fileName;

    public DataSource(AppProps props) {
        String[] split = props.getCsvFileName().split("\\.");
        fileName = split[0] + "_" + props.getLocale() + "." + split[1];
    }

    public String getFileName() {
        return fileName;
    }
}

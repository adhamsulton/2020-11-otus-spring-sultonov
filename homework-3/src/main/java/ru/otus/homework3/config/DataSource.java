package ru.otus.homework3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DataSource {
    private final String fileName;

    public DataSource(@Value("${csv-filename_en_US}") String fileName_en_us, @Value("${csv-filename_ru_RU}") String fileName_ru_ru, AppProps props) {
        if (props.getLocale().equals(Locale.US)) {
            fileName = fileName_en_us;
        } else {
            fileName = fileName_ru_ru;
        }
    }

    public String getFileName() {
        return fileName;
    }
}

package ru.otus.homework1.config;

public class DataSource {
    private final String fileName;

    public DataSource(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

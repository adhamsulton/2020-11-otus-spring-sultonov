package ru.otus.xmlconfiguration.config;

public class DataSource {
    private final String fileName;

    public DataSource(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

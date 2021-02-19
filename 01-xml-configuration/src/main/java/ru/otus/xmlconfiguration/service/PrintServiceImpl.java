package ru.otus.xmlconfiguration.service;

import java.io.PrintStream;

public class PrintServiceImpl implements PrintService {
    private final PrintStream printStream;

    public PrintServiceImpl(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print(String text) {
        printStream.println(text);
    }
}

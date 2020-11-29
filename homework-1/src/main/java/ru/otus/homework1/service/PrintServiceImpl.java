package ru.otus.homework1.service;

import java.io.PrintStream;

public class PrintServiceImpl implements PrintService {
    private final PrintStream printStream;

    public PrintServiceImpl(PrintStream printStream) {
        this.printStream = printStream;
//        this.printStream = System.out; Так работает. Но я не знаю как это инжектить в контексте
    }

    @Override
    public void print(String text) {
        printStream.println(text);
    }
}
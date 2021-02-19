package ru.otus.springshell.service;

import java.io.PrintStream;
import java.util.Scanner;

public class ReadWriteServiceImpl implements ReadWriteService {
    private final PrintStream printStream;
    private final Scanner scanner;

    public ReadWriteServiceImpl(PrintStream printStream, Scanner scanner) {
        this.printStream = printStream;
        this.scanner = scanner;
    }

    @Override
    public void print(String text) {
        printStream.println(text);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}

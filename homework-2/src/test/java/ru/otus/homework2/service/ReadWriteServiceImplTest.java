package ru.otus.homework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ReadWriteServiceImplTest {
    private ReadWriteService readWriteService;

    @BeforeEach
    void setUp() {
        readWriteService = new ReadWriteServiceImpl(System.out, new Scanner(System.in));
    }

    @Test
    void print() {
        readWriteService.print(UUID.randomUUID().toString());
    }

}
package ru.otus.javabasedconfiguration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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
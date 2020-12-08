package ru.otus.homework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private ReadWriteService readWriteService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(readWriteService);
    }

    @Test
    void getUserFullName() {
        userService.getUserFullName();
    }
}
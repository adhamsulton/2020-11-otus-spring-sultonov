package ru.otus.homework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework2.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
//        when(userService.getUser()).thenReturn(new User("Ivan"));
//        assertEquals(userService.getUser().getFullName(),"Ivan");
    }
}
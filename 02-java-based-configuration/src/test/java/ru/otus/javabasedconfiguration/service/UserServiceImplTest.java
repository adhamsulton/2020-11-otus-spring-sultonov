package ru.otus.javabasedconfiguration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.javabasedconfiguration.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void getUser() {
        String expectedUser = "Ivan";
        when(readWriteService.read()).thenReturn(expectedUser);
        User user = userService.getUser();

        assertNotNull(user);
        assertEquals(expectedUser, user.getFullName());
    }
}
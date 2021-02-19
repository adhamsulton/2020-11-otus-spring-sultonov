package ru.otus.springboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springboot.domain.User;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
class QuestionServiceImplTest {
    @MockBean
    private ReadWriteService readWriteService;
    @MockBean
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        User user = new User("Ivan");
        when(userService.getUser()).thenReturn(user);
        when(readWriteService.read()).thenReturn("1");
    }

    @Test
    void runTest() {
        questionService.runTest();
        verify(userService, times(1)).getUser();
        verify(readWriteService, times(1)).print("Welcome to testing " + "Ivan!");
    }
}
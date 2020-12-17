package ru.otus.homework3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import ru.otus.homework3.config.AppProps;
import ru.otus.homework3.dao.QuestionDao;
import ru.otus.homework3.domain.User;

import java.util.Scanner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest(classes = QuestionServiceImpl.class)
class QuestionServiceImplTest {
    @Autowired
    private ReadWriteService readWriteService;
    @MockBean
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @TestConfiguration
    class NestedConfiguration {
        @Bean
        ReadWriteService readWriteService() {
            return new ReadWriteServiceImpl(System.out, new Scanner(System.in));
        }
    }

    @BeforeEach
    public void setUp() {
        User user = new User("Ivan");
        when(userService.getUser()).thenReturn(user);
    }

    @Test
    void runTest() {
        questionService.runTest();
        verify(userService, times(1)).getUser();
        verify(readWriteService, times(1)).print("Welcome to testing " + "Ivan");
    }
}
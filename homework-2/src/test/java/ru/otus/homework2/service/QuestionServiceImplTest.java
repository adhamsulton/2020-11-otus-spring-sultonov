package ru.otus.homework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import ru.otus.homework2.dao.QuestionDao;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
    @Mock
    private QuestionDao questionDao;
    @Mock
    private ReadWriteService readWriteService;
    @Mock
    private UserService userService;
    @Value("pass-mark")
    private int passMark;

    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionDao, userService, readWriteService, passMark);
    }

    @Test
    void runTest() {
        questionService.runTest();
    }
}
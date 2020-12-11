package ru.otus.homework2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework2.dao.QuestionDao;
import ru.otus.homework2.domain.User;
import ru.otus.homework2.exception.QuestionsReadingException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
    @Mock
    private QuestionDao questionDao;
    @Mock
    private ReadWriteService readWriteService;
    @Mock
    private UserService userService;
    private int passMark = 3;

    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionDao, userService, readWriteService, passMark);
    }

    @Test
    void runTest() throws QuestionsReadingException {
        User user = new User("Ivan");
        when(userService.getUser()).thenReturn(user);

        questionService.runTest();
        verify(userService, times(1)).getUser();
        verify(readWriteService, times(1)).print("Welcome to testing " + user.getFullName());
    }
}
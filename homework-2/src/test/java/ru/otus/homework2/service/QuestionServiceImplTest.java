package ru.otus.homework2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework2.dao.QuestionDao;
import ru.otus.homework2.domain.Answer;
import ru.otus.homework2.domain.Question;
import ru.otus.homework2.exception.QuestionsReadingException;

import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
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
        Question question = new Question();
        question.setQuestionText("question");
        question.setAnswerList(List.of(new Answer("ans", false)));
        when(questionDao.getQuestionList()).thenReturn(List.of(question));
        assertThat(questionDao.getQuestionList().get(0).getQuestionText()).isEqualTo(question.getQuestionText());
        assertThat(questionDao.getQuestionList().get(0).getAnswerList().size()).isEqualTo(question.getAnswerList().size());

        questionService.runTest();
        verify(userService, times(1)).getUser();
    }
}
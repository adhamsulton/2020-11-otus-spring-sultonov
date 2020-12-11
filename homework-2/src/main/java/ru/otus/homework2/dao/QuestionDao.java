package ru.otus.homework2.dao;

import ru.otus.homework2.domain.Question;
import ru.otus.homework2.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws QuestionsReadingException;
}

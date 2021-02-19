package ru.otus.javabasedconfiguration.dao;

import ru.otus.javabasedconfiguration.domain.Question;
import ru.otus.javabasedconfiguration.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws QuestionsReadingException;
}

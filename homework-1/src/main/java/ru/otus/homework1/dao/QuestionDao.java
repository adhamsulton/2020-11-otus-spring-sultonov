package ru.otus.homework1.dao;

import ru.otus.homework1.domain.Question;
import ru.otus.homework1.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws QuestionsReadingException;
}

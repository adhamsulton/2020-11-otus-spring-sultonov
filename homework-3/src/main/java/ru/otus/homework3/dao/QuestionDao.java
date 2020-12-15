package ru.otus.homework3.dao;

import ru.otus.homework3.domain.Question;
import ru.otus.homework3.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws QuestionsReadingException;
}

package ru.otus.xmlconfiguration.dao;

import ru.otus.xmlconfiguration.domain.Question;
import ru.otus.xmlconfiguration.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws QuestionsReadingException;
}

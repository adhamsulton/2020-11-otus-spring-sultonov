package ru.otus.springboot.dao;

import ru.otus.springboot.domain.Question;
import ru.otus.springboot.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws QuestionsReadingException;
}

package ru.otus.homework4.dao;

import ru.otus.homework4.domain.Question;
import ru.otus.homework4.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws QuestionsReadingException;
}

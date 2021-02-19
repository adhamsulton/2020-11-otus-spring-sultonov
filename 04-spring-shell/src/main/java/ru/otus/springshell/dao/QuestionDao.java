package ru.otus.springshell.dao;

import ru.otus.springshell.domain.Question;
import ru.otus.springshell.exception.QuestionsReadingException;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws QuestionsReadingException;
}

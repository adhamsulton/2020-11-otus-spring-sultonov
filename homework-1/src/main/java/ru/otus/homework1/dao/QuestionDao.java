package ru.otus.homework1.dao;

import ru.otus.homework1.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> getQuestionList() throws IOException;
}

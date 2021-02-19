package ru.otus.javabasedconfiguration.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String questionText;
    private List<Answer> answerList;
}

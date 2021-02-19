package ru.otus.xmlconfiguration.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String questionText;
    private List<Answer> answerList;
}

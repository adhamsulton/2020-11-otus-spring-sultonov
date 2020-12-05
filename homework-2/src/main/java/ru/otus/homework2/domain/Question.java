package ru.otus.homework2.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String questionText;
    private List<Answer> answerList;
}

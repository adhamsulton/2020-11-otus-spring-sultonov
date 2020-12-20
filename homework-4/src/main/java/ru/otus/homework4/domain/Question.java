package ru.otus.homework4.domain;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String questionText;
    private List<Answer> answerList;
}

package ru.otus.homework3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private String answerText;
    private Boolean correct;
}

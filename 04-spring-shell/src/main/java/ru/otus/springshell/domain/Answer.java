package ru.otus.springshell.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private String answerText;
    private Boolean correct;
}

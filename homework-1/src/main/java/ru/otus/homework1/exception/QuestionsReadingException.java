package ru.otus.homework1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionsReadingException extends Exception {
    private String message;
}

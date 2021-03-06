package ru.otus.xmlconfiguration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionsReadingException extends Exception {
    private String message;
    private Exception originalException;

    public QuestionsReadingException(String message) {
        super(message);
    }
}

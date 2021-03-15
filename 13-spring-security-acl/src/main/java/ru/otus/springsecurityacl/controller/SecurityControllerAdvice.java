package ru.otus.springsecurityacl.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SecurityControllerAdvice {
    @ExceptionHandler(AccessDeniedException.class)
    public String accessError() {
        return "error403";
    }
}

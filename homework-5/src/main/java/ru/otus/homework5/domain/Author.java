package ru.otus.homework5.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Author {
    private final Long id;
    private final String fullName;
}

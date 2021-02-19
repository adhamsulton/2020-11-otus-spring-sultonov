package ru.otus.springjdbc.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Genre {
    private final Long id;
    private final String name;
}

package ru.otus.homework5.domain;

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

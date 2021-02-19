package ru.otus.springjdbc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthors {
    private Long bookId;
    private Long authorId;
    private String authorName;
}

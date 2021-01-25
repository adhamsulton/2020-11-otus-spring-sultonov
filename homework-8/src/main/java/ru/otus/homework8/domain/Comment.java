package ru.otus.homework8.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("comments")
public class Comment {
    @Id
    private String id;
    private String text;
    private LocalDateTime createdOn;
    @DBRef
    private Book book;
}

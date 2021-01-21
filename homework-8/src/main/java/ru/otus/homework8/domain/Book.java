package ru.otus.homework8.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("books")
public class Book {
    @Id
    private String id;
    private String name;
    private List<Author> authorList;
    private Genre genre;
//    @ToString.Exclude
//    @DBRef
//    @Field("comment")
//    private List<Comment> commentList;
}

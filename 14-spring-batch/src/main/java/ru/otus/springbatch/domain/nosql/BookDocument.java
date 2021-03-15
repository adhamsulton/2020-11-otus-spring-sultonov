package ru.otus.springbatch.domain.nosql;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("books")
public class BookDocument {
    @Id
    private String id;
    private String name;
    private List<AuthorDocument> authorList;
    private GenreDocument genre;
    @ToString.Exclude
    @Transient
    private List<CommentDocument> commentList;
}

package ru.otus.springbatch.domain.nosql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("comments")
public class CommentDocument {
    @Id
    private String id;
    private String text;
    private LocalDateTime createdOn;
    @DBRef
    private BookDocument book;
}

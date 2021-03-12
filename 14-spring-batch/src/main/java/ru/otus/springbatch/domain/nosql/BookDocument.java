package ru.otus.springbatch.domain.nosql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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
}

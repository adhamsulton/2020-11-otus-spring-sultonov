package ru.otus.homework6.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_COMMENTS_SQ")
    @SequenceGenerator(name = "BOOK_COMMENTS_SQ", sequenceName = "BOOK_COMMENTS_SQ", allocationSize = 1)
    private Long id;
    private String text;
    private LocalDateTime createdOn;
    @ToString.Exclude
    @ManyToOne
    private Book book;
}

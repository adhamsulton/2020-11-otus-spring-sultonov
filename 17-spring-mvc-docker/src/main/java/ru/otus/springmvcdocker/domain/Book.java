package ru.otus.springmvcdocker.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "Book.genre",
        attributeNodes = @NamedAttributeNode("genre"))
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKS_SQ")
    @SequenceGenerator(name = "BOOKS_SQ", sequenceName = "BOOKS_SQ", allocationSize = 1)
    private Long id;
    private String name;
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authorList;
    @Fetch(FetchMode.JOIN)
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @ToString.Exclude
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;

    public Book(Long id, String name, List<Author> authorList, Genre genre) {
        this.id = id;
        this.name = name;
        this.authorList = authorList;
        this.genre = genre;
    }

}

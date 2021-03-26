package ru.otus.springmicroservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.springmicroservice.domain.Book;

import java.util.List;

@RepositoryRestResource(path = "books")
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "Book.genre", type = EntityGraph.EntityGraphType.LOAD)
    List<Book> findAll();
}

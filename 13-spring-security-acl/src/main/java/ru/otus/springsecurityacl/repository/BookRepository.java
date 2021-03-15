package ru.otus.springsecurityacl.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springsecurityacl.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "Book.genre", type = EntityGraph.EntityGraphType.LOAD)
    List<Book> findAll();

    @SuppressWarnings("unchecked")
    Book save(Book book);

    void deleteById(Long id);
}

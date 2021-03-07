package ru.otus.springsecurityacl.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ru.otus.springsecurityacl.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @PostFilter("hasPermission(filterObject, 'READ')")
    @EntityGraph(value = "Book.genre", type = EntityGraph.EntityGraphType.LOAD)
    List<Book> findAll();

    @SuppressWarnings("unchecked")
    @PreAuthorize("#book.id != null ? hasPermission(#book, 'WRITE') : hasRole('ROLE_ADMIN')")
    Book save(Book book);

    @PreAuthorize("hasPermission(#id, 'ru.otus.springsecurityacl.domain.Book', 'DELETE')")
    void deleteById(Long id);
}

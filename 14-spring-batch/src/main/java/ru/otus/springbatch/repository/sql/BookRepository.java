package ru.otus.springbatch.repository.sql;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springbatch.domain.sql.Book;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    @EntityGraph(value = "Book.genre", type = EntityGraph.EntityGraphType.LOAD)
    List<Book> findAll();
}

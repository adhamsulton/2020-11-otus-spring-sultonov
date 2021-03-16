package ru.otus.springbatch.repository.sql;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springbatch.domain.sql.Book;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Book, Long> {
}

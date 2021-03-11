package ru.otus.springbatch.repository.sql;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springbatch.domain.sql.Book;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Book, Long> {
//    List<Book> findAllByBook_Id(Long bookId);
}

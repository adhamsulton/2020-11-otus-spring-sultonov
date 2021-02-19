package ru.otus.springwebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springwebflux.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
    List<Comment> findAllByBook_Id(String bookId);

    void deleteAllByBook_Id(String bookId);
}

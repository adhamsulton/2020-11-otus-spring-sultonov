package ru.otus.springdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springdatamongodb.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findAllByBook_Id(String bookId);

    void deleteAllByBook_Id(String bookId);
}

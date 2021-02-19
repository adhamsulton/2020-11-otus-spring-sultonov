package ru.otus.springormhibernatejpql.repository;

import ru.otus.springormhibernatejpql.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> findAllByBookId(Long bookId);

    Optional<Comment> findById(Long id);

    Comment save(Comment comment);

    void delete(Long id);
}

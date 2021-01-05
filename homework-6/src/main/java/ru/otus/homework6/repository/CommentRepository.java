package ru.otus.homework6.repository;

import ru.otus.homework6.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    List<Comment> findAll();

    Optional<Comment> findById(Long id);

    Comment save(Comment comment);

    void delete(Long id);
}

package ru.otus.springormhibernatejpql.service;

import ru.otus.springormhibernatejpql.domain.Comment;
import ru.otus.springormhibernatejpql.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

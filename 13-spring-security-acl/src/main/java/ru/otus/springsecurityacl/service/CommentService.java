package ru.otus.springsecurityacl.service;

import ru.otus.springsecurityacl.domain.Comment;
import ru.otus.springsecurityacl.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

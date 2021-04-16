package ru.otus.springmvcdocker.service;

import ru.otus.springmvcdocker.domain.Comment;
import ru.otus.springmvcdocker.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

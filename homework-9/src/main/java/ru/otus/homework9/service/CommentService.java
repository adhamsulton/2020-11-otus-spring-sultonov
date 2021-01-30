package ru.otus.homework9.service;

import ru.otus.homework9.domain.Comment;
import ru.otus.homework9.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

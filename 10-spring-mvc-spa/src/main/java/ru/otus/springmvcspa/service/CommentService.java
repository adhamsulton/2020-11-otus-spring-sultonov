package ru.otus.springmvcspa.service;

import ru.otus.springmvcspa.domain.Comment;
import ru.otus.springmvcspa.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

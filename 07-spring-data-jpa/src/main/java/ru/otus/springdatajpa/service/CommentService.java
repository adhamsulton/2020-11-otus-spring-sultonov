package ru.otus.springdatajpa.service;

import ru.otus.springdatajpa.domain.Comment;
import ru.otus.springdatajpa.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

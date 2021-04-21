package ru.otus.springhystrix.service;

import ru.otus.springhystrix.domain.Comment;
import ru.otus.springhystrix.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

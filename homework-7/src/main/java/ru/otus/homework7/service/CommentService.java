package ru.otus.homework7.service;

import ru.otus.homework7.domain.Comment;
import ru.otus.homework7.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

package ru.otus.springmvcview.service;

import ru.otus.springmvcview.domain.Comment;
import ru.otus.springmvcview.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(Long bookId);

    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

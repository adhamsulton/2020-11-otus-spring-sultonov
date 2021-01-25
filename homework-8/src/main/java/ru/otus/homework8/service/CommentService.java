package ru.otus.homework8.service;

import ru.otus.homework8.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(String bookId);

    void create( String bookId, String text) throws Exception;

    void update(String commentId, String text) throws Exception;

    void delete(String id);
}

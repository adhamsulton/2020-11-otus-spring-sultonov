package ru.otus.springdatamongodb.service;

import ru.otus.springdatamongodb.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllBookComments(String bookId);

    void create( String bookId, String text) throws Exception;

    void update(String commentId, String text) throws Exception;

    void delete(String id);
}

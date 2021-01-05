package ru.otus.homework6.service;

import ru.otus.homework6.dto.CommentDto;

public interface CommentService {



    void create(CommentDto commentDto) throws Exception;

    void update(CommentDto commentDto) throws Exception;

    void delete(Long id);
}

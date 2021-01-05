package ru.otus.homework6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework6.domain.Comment;
import ru.otus.homework6.dto.CommentDto;
import ru.otus.homework6.repository.BookRepository;
import ru.otus.homework6.repository.CommentRepository;

import java.time.LocalDateTime;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final BookRepository bookRepository;

    @Override
    public void create(CommentDto commentDto) throws Exception {
        repository.save(Comment.builder()
                .text(commentDto.getText())
                .createdOn(LocalDateTime.now())
                .book(bookRepository.findById(commentDto.getBookId()).orElseThrow(() -> new Exception("Book not found")))
                .build()
        );
    }

    @Override
    public void update(CommentDto commentDto) throws Exception {
        Comment comment = repository.findById(commentDto.getId()).orElseThrow(() -> new Exception("Comment not found"));

        comment.setText(commentDto.getText());

        repository.save(comment);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}

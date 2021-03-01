package ru.otus.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springsecurity.domain.Comment;
import ru.otus.springsecurity.dto.CommentDto;
import ru.otus.springsecurity.repository.BookRepository;
import ru.otus.springsecurity.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAllBookComments(Long bookId) {
        return commentRepository.findAllByBook_Id(bookId);
    }

    @Transactional
    @Override
    public void create(CommentDto commentDto) throws Exception {
        commentRepository.save(Comment.builder()
                .text(commentDto.getText())
                .createdOn(LocalDateTime.now())
                .book(bookRepository.findById(commentDto.getBookId()).orElseThrow(() -> new Exception("Book not found")))
                .build()
        );
    }

    @Transactional
    @Override
    public void update(CommentDto commentDto) throws Exception {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(() -> new Exception("Comment not found"));

        comment.setText(commentDto.getText());

        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}

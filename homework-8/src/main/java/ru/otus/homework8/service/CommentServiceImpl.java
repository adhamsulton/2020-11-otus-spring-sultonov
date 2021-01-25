package ru.otus.homework8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework8.domain.Book;
import ru.otus.homework8.domain.Comment;
import ru.otus.homework8.repository.BookRepository;
import ru.otus.homework8.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAllBookComments(String bookId) {
        return commentRepository.findAllByBook_Id(bookId);
    }

    @Transactional
    @Override
    public void create(String bookId, String text) throws Exception {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));

        Comment comment = Comment.builder()
                .text(text)
                .createdOn(LocalDateTime.now())
                .book(book)
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void update(String commentId, String text) throws Exception {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new Exception("Comment not found"));

        comment.setText(text);

        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void delete(String id) {
        commentRepository.deleteById(id);
    }
}

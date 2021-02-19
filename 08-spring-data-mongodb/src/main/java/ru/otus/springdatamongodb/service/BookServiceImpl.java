package ru.otus.springdatamongodb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springdatamongodb.domain.Book;
import ru.otus.springdatamongodb.repository.BookRepository;
import ru.otus.springdatamongodb.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(String id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Book not found"));
    }

    @Transactional
    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Transactional
    @Override
    public void delete(String id) {
        repository.deleteById(id);
        commentRepository.deleteAllByBook_Id(id);
    }
}

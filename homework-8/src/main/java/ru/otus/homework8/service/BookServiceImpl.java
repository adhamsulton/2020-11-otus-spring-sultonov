package ru.otus.homework8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework8.domain.Book;
import ru.otus.homework8.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

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
    }
}

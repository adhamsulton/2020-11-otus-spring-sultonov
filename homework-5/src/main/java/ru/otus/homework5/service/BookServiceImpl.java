package ru.otus.homework5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework5.dao.BookDao;
import ru.otus.homework5.domain.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public void create(String name, Long genreId, List<Long> authorIdList) {
        Book book = Book.builder().name(name).genreId(genreId).authorIds(authorIdList).build();
        bookDao.create(book);
    }

    @Override
    public void update(Long id, String name, Long genreId, List<Long> authorIdList) {
        Book book = Book.builder().id(id).name(name).genreId(genreId).authorIds(authorIdList).build();

        bookDao.update(book);
    }

    @Override
    public void delete(Long bookId) {
        bookDao.delete(bookId);
    }
}

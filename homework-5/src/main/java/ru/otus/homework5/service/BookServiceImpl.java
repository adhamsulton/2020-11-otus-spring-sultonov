package ru.otus.homework5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework5.dao.BookDao;
import ru.otus.homework5.domain.Book;
import ru.otus.homework5.dto.BookDto;

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
    public Book findById(Long id) throws Exception {
        return bookDao.findById(id).orElseThrow(() -> new Exception("Book not found"));
    }

    @Override
    public void create(String name, Long genreId, List<Long> authorIdList) {
        BookDto bookDto = BookDto.builder().name(name).genreId(genreId).authorIds(authorIdList).build();
        bookDao.create(bookDto);
    }

    @Override
    public void update(Long id, String name, Long genreId, List<Long> authorIdList) {
        BookDto book = BookDto.builder().id(id).name(name).genreId(genreId).authorIds(authorIdList).build();

        bookDao.update(book);
    }

    @Override
    public void delete(Long bookId) {
        bookDao.delete(bookId);
    }
}

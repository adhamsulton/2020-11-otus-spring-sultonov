package ru.otus.springbatch.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.otus.springbatch.domain.nosql.BookDocument;
import ru.otus.springbatch.domain.sql.Book;

@Service
public class BookConvertServiceImpl implements BookConvertService {

    @Override
    public BookDocument convert(Book book) {
        ModelMapper mapper = new ModelMapper();
        BookDocument bookDocument = mapper.map(book, BookDocument.class);
        bookDocument.setId(null);
        return bookDocument;
    }
}

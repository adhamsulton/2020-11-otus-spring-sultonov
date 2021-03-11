package ru.otus.springbatch.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.otus.springbatch.domain.nosql.BookDocument;
import ru.otus.springbatch.domain.sql.Book;

@Service
public class BookConvertServiceImpl implements BookConvertService {

    @Override
    public BookDocument convert(Book book) {
        System.out.println("1111111111");
        System.out.println(book);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(book, BookDocument.class);
    }
}

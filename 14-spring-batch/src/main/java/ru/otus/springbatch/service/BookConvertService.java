package ru.otus.springbatch.service;

import ru.otus.springbatch.domain.nosql.BookDocument;
import ru.otus.springbatch.domain.sql.Book;

public interface BookConvertService {
    BookDocument convert(Book book);
}

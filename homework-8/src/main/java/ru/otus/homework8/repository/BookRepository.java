package ru.otus.homework8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework8.domain.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}

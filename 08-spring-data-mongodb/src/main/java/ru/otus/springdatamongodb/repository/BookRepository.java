package ru.otus.springdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springdatamongodb.domain.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}

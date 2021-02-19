package ru.otus.homework11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework11.domain.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}

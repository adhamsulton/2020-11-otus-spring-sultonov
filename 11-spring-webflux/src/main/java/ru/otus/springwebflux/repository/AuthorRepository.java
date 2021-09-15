package ru.otus.springwebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springwebflux.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}

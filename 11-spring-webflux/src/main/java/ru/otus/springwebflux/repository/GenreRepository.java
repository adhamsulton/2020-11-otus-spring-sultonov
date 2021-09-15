package ru.otus.springwebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springwebflux.domain.Genre;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}

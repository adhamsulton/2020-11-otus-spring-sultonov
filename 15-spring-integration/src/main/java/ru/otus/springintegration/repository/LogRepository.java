package ru.otus.springintegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springintegration.domain.Log;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {
}

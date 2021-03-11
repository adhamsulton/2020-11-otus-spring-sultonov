package ru.otus.springbatch.repository.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springbatch.domain.nosql.BookDocument;

@Repository
public interface BookDocumentRepository extends MongoRepository<BookDocument, String> {
}

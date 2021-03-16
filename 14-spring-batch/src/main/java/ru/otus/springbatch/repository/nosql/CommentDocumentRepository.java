package ru.otus.springbatch.repository.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springbatch.domain.nosql.CommentDocument;

@Repository
public interface CommentDocumentRepository extends MongoRepository<CommentDocument, String> {
}

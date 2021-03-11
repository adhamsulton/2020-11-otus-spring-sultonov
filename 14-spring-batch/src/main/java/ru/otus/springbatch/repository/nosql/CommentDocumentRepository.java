package ru.otus.springbatch.repository.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springbatch.domain.nosql.CommentDocument;

import java.util.List;

@Repository
public interface CommentDocumentRepository extends MongoRepository<CommentDocument, String> {
//    List<CommentDocument> findAllByBook_Id(String bookId);

//    void deleteAllByBook_Id(String bookId);
}

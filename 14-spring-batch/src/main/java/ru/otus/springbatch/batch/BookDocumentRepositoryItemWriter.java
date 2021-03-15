package ru.otus.springbatch.batch;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import ru.otus.springbatch.domain.nosql.BookDocument;
import ru.otus.springbatch.domain.nosql.CommentDocument;
import ru.otus.springbatch.repository.nosql.BookDocumentRepository;
import ru.otus.springbatch.repository.nosql.CommentDocumentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookDocumentRepositoryItemWriter<T> implements ItemWriter<BookDocument>, InitializingBean {
    private final BookDocumentRepository bookDocumentRepository;
    private final CommentDocumentRepository commentDocumentRepository;

    @Override
    public void write(List<? extends BookDocument> items) throws Exception {
        List<CommentDocument> allCommentList = new ArrayList<>();

        for (BookDocument bookDocument : items) {
            Object objectId = bookDocument.getId() != null ? bookDocument.getId() : new ObjectId();
            bookDocument.setId(objectId.toString());

            List<CommentDocument> commentList = bookDocument.getCommentList()
                    .stream()
                    .map(x -> CommentDocument
                            .builder()
                            .text(x.getText())
                            .createdOn(x.getCreatedOn())
                            .book(bookDocument)
                            .build())
                    .collect(Collectors.toList());
            allCommentList.addAll(commentList);
        }
        bookDocumentRepository.saveAll(items);
        commentDocumentRepository.saveAll(allCommentList);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}

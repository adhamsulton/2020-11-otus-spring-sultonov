package ru.otus.springbatch.batch;

import org.bson.types.ObjectId;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.springbatch.domain.nosql.BookDocument;
import ru.otus.springbatch.domain.nosql.CommentDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDocumentRepositoryItemWriter<T> implements ItemWriter<BookDocument>, InitializingBean {
    private final MongoItemWriter<BookDocument> bookWriter;
    private final MongoItemWriter<CommentDocument> commentWriter;

    public BookDocumentRepositoryItemWriter(MongoOperations template) {
        this.bookWriter = new MongoItemWriterBuilder<BookDocument>().template(template).build();
        this.commentWriter = new MongoItemWriterBuilder<CommentDocument>().template(template).build();
    }

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

        bookWriter.write(items);
        commentWriter.write(allCommentList);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}

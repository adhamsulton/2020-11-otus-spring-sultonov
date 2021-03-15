package ru.otus.springbatch.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import ru.otus.springbatch.domain.nosql.BookDocument;
import ru.otus.springbatch.domain.nosql.CommentDocument;
import ru.otus.springbatch.repository.nosql.BookDocumentRepository;
import ru.otus.springbatch.repository.nosql.CommentDocumentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookDocumentRepositoryItemWriter<T> implements ItemWriter<BookDocument>, InitializingBean {
    private final BookDocumentRepository bookDocumentRepository;
    private final CommentDocumentRepository commentDocumentRepository;

    @Override
    public void write(List<? extends BookDocument> items) throws Exception {
        for (BookDocument bookDocument : items) {
            BookDocument savedBookDocument = bookDocumentRepository.save(bookDocument);

            List<CommentDocument> commentList = bookDocument.getCommentList()
                    .stream()
                    .map(x -> CommentDocument
                            .builder()
                            .text(x.getText())
                            .createdOn(x.getCreatedOn())
                            .book(savedBookDocument)
                            .build())
                    .collect(Collectors.toList());
            commentDocumentRepository.saveAll(commentList);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}

package ru.otus.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.springbatch.batch.BookDocumentRepositoryItemWriter;
import ru.otus.springbatch.domain.nosql.BookDocument;
import ru.otus.springbatch.domain.sql.Book;
import ru.otus.springbatch.repository.nosql.BookDocumentRepository;
import ru.otus.springbatch.repository.nosql.CommentDocumentRepository;
import ru.otus.springbatch.repository.sql.BookRepository;
import ru.otus.springbatch.service.BookConvertService;

import java.util.HashMap;
import java.util.List;

@Slf4j
@SuppressWarnings("all")
@EnableBatchProcessing
@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 5;
    public static final String IMPORT_BOOK_JOB = "importBookJob";

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookDocumentRepository bookDocumentRepository;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @StepScope
    @Bean
    public RepositoryItemReader<Book> reader() {
        return new RepositoryItemReaderBuilder<Book>()
                .name("bookItemReader")
                .repository(bookRepository)
                .methodName("findAll")
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Book, BookDocument> processor(BookConvertService bookConvertService) {
        return bookConvertService::convert;
    }

    @StepScope
    @Bean
    public BookDocumentRepositoryItemWriter writer(BookDocumentRepository bookDocumentRepository,
                                                   CommentDocumentRepository commentDocumentRepository) {
        return new BookDocumentRepositoryItemWriter(bookDocumentRepository, commentDocumentRepository);
    }

    @Bean
    public Job importBookJob(Step step1) {
        return jobBuilderFactory.get(IMPORT_BOOK_JOB)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step step1(BookDocumentRepositoryItemWriter<BookDocument> writer, RepositoryItemReader<Book> reader, ItemProcessor<Book, BookDocument> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<Book, BookDocument>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {
                        log.info("Начало чтения");
                    }

                    public void afterRead(Book book) {
                        log.info("Конец чтения");
                    }

                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(List list) {
                        log.info("Начало записи");
                    }

                    public void afterWrite(List list) {
                        log.info("Конец записи");
                    }

                    public void onWriteError(Exception e, List list) {
                        log.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Book book) {
                        log.info("Начало обработки");
                    }

                    public void afterProcess(Book book, BookDocument bookDocument) {
                        log.info("Конец обработки");
                    }

                    public void onProcessError(Book book, Exception e) {
                        log.info("Ошбка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(ChunkContext chunkContext) {
                        log.info("Начало пачки");
                    }

                    public void afterChunk(ChunkContext chunkContext) {
                        log.info("Конец пачки");
                    }

                    public void afterChunkError(ChunkContext chunkContext) {
                        log.info("Ошибка пачки");
                    }
                })
                .build();
    }
}

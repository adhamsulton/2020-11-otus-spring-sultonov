package ru.otus.homework11.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.homework11.domain.Author;
import ru.otus.homework11.domain.Book;
import ru.otus.homework11.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Test
    void shouldSave() {
        Mono<Book> bookMono = repository.save(
                new Book(null, "new book",
                        List.of(new Author("1", "Стивен Кинг")),
                        new Genre("1", "Детектив")));

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertThat(bookMono))
                .expectComplete()
                .verify();
    }

    @Test
    void shouldFindById() {
        Book book = new Book("3", "new book",
                List.of(new Author("1", "Стивен Кинг")),
                new Genre("1", "Детектив"));

        repository.save(book).block();

        StepVerifier.create(repository.findById("3"))
                .assertNext(b -> assertThat(book))
                .expectComplete()
                .verify();
    }

}
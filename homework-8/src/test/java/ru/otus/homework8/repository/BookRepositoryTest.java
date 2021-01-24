package ru.otus.homework8.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework8.domain.Author;
import ru.otus.homework8.domain.Book;
import ru.otus.homework8.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Mongo репозиторий для работы с книгами должен")
@DataMongoTest
class BookRepositoryTest {
    private static final String BOOK_ID = "1";

    @Autowired
    private BookRepository repository;
    @Autowired
    private MongoTemplate mt;

    @DisplayName("найти книгу по Ид")
    @Test
    void findById() {
        Optional<Book> optionalActualBook = repository.findById(BOOK_ID);
        Book expectedBook = mt.findById(BOOK_ID, Book.class);

        assertThat(optionalActualBook).isPresent().get().usingRecursiveComparison()
                .ignoringCollectionOrder().isEqualTo(expectedBook);
    }

    @DisplayName("добавить в Бд новую книгу")
    @Test
    void create() {
        Book expectedBook = new Book("2", "new book", List.of(new Author("2", "Стивен Кинг")), new Genre("2", "Детектив"));
        Book savedBook = repository.save(expectedBook);

        Book actualBook = mt.findById(expectedBook.getId(), Book.class);

        assertThat(actualBook).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedBook);

        assertThat(savedBook).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedBook);
    }

}
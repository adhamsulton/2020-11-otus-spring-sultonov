package ru.otus.homework7.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework7.domain.Author;
import ru.otus.homework7.domain.Book;
import ru.otus.homework7.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA репозиторий для работы с книгами должен")
@DataJpaTest
class BookRepositoryTest {
    private static final Long BOOK_ID = 1L;

    @Autowired
    private BookRepository repository;
    @Autowired
    private TestEntityManager em;

    @DisplayName("найти книгу по Ид")

    @Test
    void findById() {
        Optional<Book> optionalActualBook = repository.findById(BOOK_ID);
        Book expectedBook = em.find(Book.class, BOOK_ID);

        assertThat(optionalActualBook).isPresent().get().usingRecursiveComparison()
                .ignoringCollectionOrder().isEqualTo(expectedBook);
    }

    @DisplayName("добавить в Бд новую книгу")
    @Test
    void create() {
        Book expectedBook = new Book(3L, "new book", List.of(new Author(1L, "Стивен Кинг")), new Genre(1L, "Детектив"));
        Book savedBook = repository.save(expectedBook);
        Book actualBook = em.find(Book.class, expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedBook);

        assertThat(savedBook).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedBook);
    }

}
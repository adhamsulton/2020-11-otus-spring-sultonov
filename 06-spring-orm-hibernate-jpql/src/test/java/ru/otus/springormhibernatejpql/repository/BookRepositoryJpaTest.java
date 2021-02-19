package ru.otus.springormhibernatejpql.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.springormhibernatejpql.domain.Author;
import ru.otus.springormhibernatejpql.domain.Book;
import ru.otus.springormhibernatejpql.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("JPA репозиторий для работы с книгами должен")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {
    private static final Long BOOK_ID = 1L;
    private static final int EXPECTED_NUMBER_OF_BOOKS = 2;
    private static final long EXPECTED_QUERIES_COUNT = 2L;

    @Autowired
    private BookRepositoryJpa repositoryJpa;
    @Autowired
    private TestEntityManager em;

    @DisplayName("найти все книги")
    @Test
    void findAll() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        List<Book> actualBooks = repositoryJpa.findAll();

        assertThat(actualBooks).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(b -> !b.getName().equals(""))
                .allMatch(b -> b.getGenre() != null)
                .allMatch(b -> b.getAuthorList() != null && b.getAuthorList().size() > 0);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName("найти книгу по Ид")
    @Test
    void findById() {
        Optional<Book> optionalActualBook = repositoryJpa.findById(BOOK_ID);
        Book expectedBook = em.find(Book.class, BOOK_ID);

        assertThat(optionalActualBook).isPresent().get().usingRecursiveComparison()
                .ignoringCollectionOrder().isEqualTo(expectedBook);
    }

    @DisplayName("добавить в Бд новую книгу")
    @Test
    void create() {
        Book expectedBook = new Book(3L, "new book", List.of(new Author(1L, "Стивен Кинг")), new Genre(1L, "Детектив"));
        Book savedBook = repositoryJpa.save(expectedBook);
        Book actualBook = em.find(Book.class, expectedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedBook);

        assertThat(savedBook).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedBook);
    }

    @DisplayName("изменить книгу")
    @Test
    void update() {
        Book expectedBook = em.find(Book.class, BOOK_ID);
        em.detach(expectedBook);

        Book editedBook = new Book();
        editedBook.setId(BOOK_ID);
        editedBook.setName("new name");
        editedBook.setAuthorList(List.of(new Author(1L, "Стивен Кинг")));
        editedBook.setGenre(new Genre(1L, "Детектив"));

        Book savedBook = repositoryJpa.save(editedBook);

        Book actualBook = em.find(Book.class, editedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().ignoringCollectionOrder().isNotEqualTo(expectedBook);

        assertThat(savedBook).usingRecursiveComparison().ignoringCollectionOrder().isNotEqualTo(expectedBook);
    }

    @DisplayName("изменить книгу")
    @Test
    void delete() {
        Book book = em.find(Book.class, BOOK_ID);
        assertNotNull(book);
        em.detach(book);

        repositoryJpa.delete(BOOK_ID);

        Book deletedBook = em.find(Book.class, BOOK_ID);
        assertNull(deletedBook);
    }
}
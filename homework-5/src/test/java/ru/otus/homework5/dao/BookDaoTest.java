package ru.otus.homework5.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework5.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDao.class)
class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @DisplayName("найти все книги")
    @Test
    void findAll() {
        var expectedBookList = List.of(new Book(1L, "Затерянный мир", List.of(2L), 1L), new Book(2L, "Темная башня", List.of(1L), 2L));
        var actualBookList = bookDao.findAll();

        assertThat(actualBookList).containsExactlyInAnyOrderElementsOf(expectedBookList);
    }

    @DisplayName("найти книгу по Ид")
    @Test
    void findById() {
        Book expectedBook = new Book(1L, "Затерянный мир", List.of(2L), 1L);

        var actualBook = bookDao.findById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("добавить в Бд новую книгу")
    @Test
    void create() {
        Book expectedBook = new Book(3L, "myBook", List.of(1L, 2L), 1L);
        bookDao.create(expectedBook);

        var actualBook = bookDao.findById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("изменить книгу")
    @Test
    void update() {
        Book expectedBook = new Book(1L, "new name", List.of(1L), 2L);
        bookDao.update(expectedBook);

        var actualBook = bookDao.findById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалить книгу")
    @Test
    void delete() {
        final var bookId = 1L;

        bookDao.delete(bookId);

        assertThatThrownBy(() -> bookDao.findById(bookId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}
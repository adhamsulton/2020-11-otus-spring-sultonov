package ru.otus.springjdbc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.springjdbc.domain.Author;
import ru.otus.springjdbc.domain.Book;
import ru.otus.springjdbc.domain.Genre;
import ru.otus.springjdbc.dto.BookDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @DisplayName("найти все книги")
    @Test
    void findAll() {
        var expectedBookList = List.of(
                new Book(1L, "Затерянный мир", List.of(new Author(2L, "Артур Конан Дойл")), new Genre(1L, "Детектив")),
                new Book(2L, "Темная башня", List.of(new Author(1L, "Стивен Кинг")), new Genre(2L, "Роман")));
        var actualBookList = bookDao.findAll();

        assertThat(actualBookList).containsExactlyInAnyOrderElementsOf(expectedBookList);
    }

    @DisplayName("найти книгу по Ид")
    @Test
    void findById() {
        Book expectedBook = new Book(1L, "Затерянный мир", List.of(new Author(2L, "Артур Конан Дойл")), new Genre(1L, "Детектив"));

        var actualBook = bookDao.findById(expectedBook.getId()).get();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("добавить в Бд новую книгу")
    @Test
    void create() {
        Book expectedBook = new Book(3L, "myBook",
                List.of(new Author(2L, "Артур Конан Дойл"), new Author(1L, "Стивен Кинг")),
                new Genre(1L, "Детектив"));

        BookDto bookDto = new BookDto(
                expectedBook.getId(),
                expectedBook.getName(),
                expectedBook.getAuthorList().stream().map(Author::getId).collect(Collectors.toList()),
                expectedBook.getGenre().getId());

        bookDao.create(bookDto);

        var actualBook = bookDao.findById(expectedBook.getId()).get();
        assertThat(actualBook).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedBook);
    }

    @DisplayName("изменить книгу")
    @Test
    void update() {
        Book expectedBook = new Book(1L, "new name",
                List.of(new Author(1L, "Стивен Кинг")),
                new Genre(2L, "Роман"));

        BookDto bookDto = new BookDto(
                expectedBook.getId(),
                expectedBook.getName(),
                expectedBook.getAuthorList().stream().map(Author::getId).collect(Collectors.toList()),
                expectedBook.getGenre().getId());

        bookDao.update(bookDto);

        var actualBook = bookDao.findById(expectedBook.getId()).get();
        assertThat(actualBook).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedBook);
    }

    @DisplayName("удалить книгу")
    @Test
    void delete() {
        final var bookId = 1L;

        bookDao.delete(bookId);

        assertThatThrownBy(() -> bookDao.findById(bookId).get())
                .isInstanceOf(NoSuchElementException.class);
    }
}
package ru.otus.homework5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.homework5.domain.Book;
import ru.otus.homework5.domain.BookAuthors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;

@RequiredArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        String query = "select t.id, t.name, t.genre_id from books t";

        List<Book> bookList = jdbcTemplate.query(query, (rs, i) -> Book.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .genreId(rs.getLong("genre_id"))
                .build());

        query = "select t.book_id, t.author_id from book_authors t";

        List<BookAuthors> bookAuthors = jdbcTemplate.query(query, (rs, i) -> new BookAuthors(
                rs.getLong("book_id"),
                rs.getLong("author_id")
        ));

        Map<Long, List<Long>> bookAuthorsMap = bookAuthors.stream()
                .collect(Collectors.groupingBy(
                        BookAuthors::getBookId,
                        mapping(BookAuthors::getAuthorId, Collectors.toList())
                ));

        bookList.forEach(book -> book.setAuthorIds(bookAuthorsMap.get(book.getId())));

        return bookList;
    }

    public Book findById(Long id) {
        String query = "select t.id, t.name, t.genre_id from books t where t.id = :id";

        Book book = jdbcTemplate.queryForObject(query, Map.of("id", id),
                (rs, i) -> Book.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .genreId(rs.getLong("genre_id"))
                        .build());

        if (book == null) return null;

        query = "select t.author_id from book_authors t where t.book_id = :id";

        List<Long> authorList = jdbcTemplate.queryForList(query, Map.of("id", id), Long.class);

        book.setAuthorIds(authorList);

        return book;
    }

    public void create(Book book) {
        Long bookId = jdbcTemplate.getJdbcTemplate().queryForObject("VALUES NEXT VALUE FOR BOOKS_SQ", Long.class);

        String query = "insert into books(id, name, genre_id) " +
                " values (:id, :name, :genre_id)";

        jdbcTemplate.update(query, Map.of("id", bookId, "name", book.getName(), "genre_id", book.getGenreId()));

        query = "insert into book_authors (book_id, author_id) values (:book_id, :author_id)";

        MapSqlParameterSource[] paramsList = book.getAuthorIds()
                .stream()
                .map(authorId -> new MapSqlParameterSource()
                        .addValue("book_id", bookId)
                        .addValue("author_id", authorId)
                )
                .toArray(MapSqlParameterSource[]::new);


        jdbcTemplate.batchUpdate(query, paramsList);
    }

    public void update(Book book) {
        jdbcTemplate.update("update books t " +
                        "  set t.name = :name, " +
                        "      t.genre_id = :genre_id " +
                        "where t.id = :id",
                Map.of("id", book.getId(), "name", book.getName(), "genre_id", book.getGenreId()));

        jdbcTemplate.update("delete book_authors where book_id = :book_id", Map.of("book_id", book.getId()));

        MapSqlParameterSource[] paramsList = book.getAuthorIds()
                .stream()
                .map(authorId -> new MapSqlParameterSource()
                        .addValue("book_id", book.getId())
                        .addValue("author_id", authorId)
                )
                .toArray(MapSqlParameterSource[]::new);

        jdbcTemplate.batchUpdate("insert into book_authors (book_id, author_id) values (:book_id, :author_id)",
                paramsList);
    }

    public void delete(Long bookId) {
        jdbcTemplate.update("delete books where id = :id", Map.of("id", bookId));
    }
}

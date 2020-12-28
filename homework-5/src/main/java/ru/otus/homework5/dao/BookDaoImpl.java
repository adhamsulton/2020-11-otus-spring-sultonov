package ru.otus.homework5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework5.domain.Author;
import ru.otus.homework5.domain.Book;
import ru.otus.homework5.domain.Genre;
import ru.otus.homework5.dto.BookDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;

@RequiredArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        final String query = "select t.id, t.name, t.genre_id, (select w.name from genres w where w.id = t.genre_id) genre_name from books t";

        List<Book> bookList = jdbcTemplate.query(query, (rs, i) -> Book.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .genre(new Genre(rs.getLong("genre_id"), rs.getString("genre_name")))
                .build());

        List<BookAuthors> bookAuthors = getAllBookAuthors();

        mapBookAuthors(bookList, bookAuthors);

        return bookList;
    }

    private List<BookAuthors> getAllBookAuthors() {
        final String query = "select t.book_id, t.author_id, k.full_name author_name from book_authors t join authors k on k.id = t.author_id";

        return jdbcTemplate.query(query, (rs, i) -> new BookAuthors(
                rs.getLong("book_id"),
                rs.getLong("author_id"),
                rs.getString("author_name")
        ));
    }

    private void mapBookAuthors(List<Book> bookList, List<BookAuthors> bookAuthors) {
        Map<Long, List<Author>> bookAuthorsMap = bookAuthors.stream()
                .collect(Collectors.groupingBy(
                        BookAuthors::getBookId,
                        mapping(x -> new Author(x.getAuthorId(), x.getAuthorName()), Collectors.toList())
                ));

        bookList.forEach(book -> book.setAuthorList(bookAuthorsMap.get(book.getId())));
    }

    public Optional<Book> findById(Long id) {
        String query = "select t.id, t.name, t.genre_id, k.name genre_name from books t join genres k on k.id = t.genre_id where t.id = :id";

        Book book = null;
        try {
            book = jdbcTemplate.queryForObject(query, Map.of("id", id),
                    (rs, i) -> Book.builder()
                            .id(rs.getLong("id"))
                            .name(rs.getString("name"))
                            .genre(new Genre(rs.getLong("genre_id"), rs.getString("genre_name")))
                            .build());
        } catch (Exception e) {
            return Optional.ofNullable(book);
        }

        query = "select t.author_id, k.full_name author_name from book_authors t join authors k on k.id = t.author_id where t.book_id = :id";

        List<Author> authorList = jdbcTemplate.query(query, Map.of("id", id),
                (rs, i) -> Author.builder()
                        .id(rs.getLong("author_id"))
                        .fullName(rs.getString("author_name"))
                        .build());

        book.setAuthorList(authorList);

        return Optional.ofNullable(book);
    }

    public void create(BookDto book) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "insert into books(name, genre_id) " +
                " values (:name, :genre_id)";

        jdbcTemplate.update(query, new MapSqlParameterSource()
                        .addValue("name", book.getName())
                        .addValue("genre_id", book.getGenreId()),
                keyHolder,
                new String[]{"id"});

        Long bookId = (Long) keyHolder.getKey();

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

    public void update(BookDto book) {
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

package ru.otus.homework8.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework8.domain.Author;
import ru.otus.homework8.domain.Book;
import ru.otus.homework8.domain.Comment;
import ru.otus.homework8.domain.Genre;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Mongo репозиторий для работы с комментариями книг должен")
@DataMongoTest
class CommentRepositoryTest {
    private static final String BOOK_ID = "1";
    private static final String COMMENT_ID = "1";
    private static final int EXPECTED_NUMBER_OF_COMMENTS = 1;

    @Autowired
    private CommentRepository repository;
    @Autowired
    private MongoTemplate mt;

    @BeforeEach
    void setUp() {
//        Book book = new Book(BOOK_ID, "new book", List.of(new Author("2", "Стивен Кинг")), new Genre("2", "Детектив"));
//        mt.save(book);

//        Comment expectedComment = Comment.builder().id(COMMENT_ID).text("new comment").createdOn(LocalDateTime.now().withNano(0)).book(book).build();
//        mt.save(expectedComment);
    }

    @DisplayName("добавить в Бд новый комментарий")
    @Test
    void create() {
        Comment expectedComment = Comment.builder().text("new comment").createdOn(LocalDateTime.now().withNano(0)).build();

        Comment savedComment = repository.save(expectedComment);
        Comment actualComment = mt.findById(expectedComment.getId(), Comment.class);


        assertThat(actualComment).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedComment);

        assertThat(savedComment).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedComment);
    }

    @Test
    void findAllByBook_Id() {
        List<Comment> actualComments = repository.findAllByBook_Id(BOOK_ID);

        assertThat(actualComments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS)
                .allMatch(c -> !c.getText().equals(""))
                .allMatch(c -> c.getCreatedOn() != null)
                .allMatch(c -> c.getBook() != null && c.getBook().getId().equals(BOOK_ID));
    }

    @DisplayName("найти комментарий по Ид")
    @Test
    void findById() {
        Comment expectedComment = mt.findById(COMMENT_ID, Comment.class);

        Optional<Comment> optionalActualComment = repository.findById(COMMENT_ID);

        assertThat(optionalActualComment).isPresent().get().usingRecursiveComparison()
                .ignoringCollectionOrder().isEqualTo(expectedComment);
    }

}
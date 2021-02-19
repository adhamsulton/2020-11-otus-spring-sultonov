package ru.otus.springdatajpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.springdatajpa.domain.Book;
import ru.otus.springdatajpa.domain.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA репозиторий для работы с комментариями книг должен")
@DataJpaTest
class CommentRepositoryTest {
    private static final long BOOK_ID = 2L;
    private static final long COMMENT_ID = 1L;
    private static final int EXPECTED_NUMBER_OF_COMMENTS = 1;
    private static final long EXPECTED_QUERIES_COUNT = 3L;

    @Autowired
    private CommentRepository repository;
    @Autowired
    private TestEntityManager em;

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
        Optional<Comment> optionalActualComment = repository.findById(COMMENT_ID);
        Comment expectedComment = em.find(Comment.class, COMMENT_ID);

        assertThat(optionalActualComment).isPresent().get().usingRecursiveComparison()
                .ignoringCollectionOrder().isEqualTo(expectedComment);
    }

    @DisplayName("добавить в Бд новый комментарий")
    @Test
    void create() {
        Comment expectedComment = Comment.builder().text("new comment").createdOn(LocalDateTime.now()).book(Book.builder().id(BOOK_ID).build()).build();

        Comment savedComment = repository.save(expectedComment);
        Comment actualComment = em.find(Comment.class, expectedComment.getId());

        assertThat(actualComment).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedComment);

        assertThat(savedComment).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedComment);
    }

}
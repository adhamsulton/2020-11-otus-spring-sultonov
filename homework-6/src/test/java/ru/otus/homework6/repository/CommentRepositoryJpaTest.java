package ru.otus.homework6.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework6.domain.Book;
import ru.otus.homework6.domain.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("JPA репозиторий для работы с комментариями книг должен")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {
    private static final long BOOK_ID = 2L;
    private static final int EXPECTED_NUMBER_OF_COMMENTS = 1;
    private static final long EXPECTED_QUERIES_COUNT = 3L;
    public static final long COMMENT_ID = 1L;

    @Autowired
    CommentRepository repositoryJpa;
    @Autowired
    TestEntityManager em;

    @DisplayName("найти все комментарии")
    @Test
    void findAllByBookId() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        List<Comment> actualComments = repositoryJpa.findAllByBookId(BOOK_ID);

        assertThat(actualComments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS)
                .allMatch(c -> !c.getText().equals(""))
                .allMatch(c -> c.getCreatedOn() != null)
                .allMatch(c -> c.getBook() != null && c.getBook().getId().equals(BOOK_ID));

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @DisplayName("найти комментарий по Ид")
    @Test
    void findById() {
        Optional<Comment> optionalActualComment = repositoryJpa.findById(COMMENT_ID);
        Comment expectedComment = em.find(Comment.class, COMMENT_ID);

        assertThat(optionalActualComment).isPresent().get().usingRecursiveComparison()
                .ignoringCollectionOrder().isEqualTo(expectedComment);
    }

    @DisplayName("добавить в Бд новый комментарий")
    @Test
    void create() {
        Comment expectedComment = Comment.builder().text("new comment").createdOn(LocalDateTime.now()).book(Book.builder().id(BOOK_ID).build()).build();

        Comment savedComment = repositoryJpa.save(expectedComment);
        Comment actualComment = em.find(Comment.class, expectedComment.getId());
        System.out.println(savedComment);
        System.out.println(actualComment);
        assertThat(actualComment).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedComment);

        assertThat(savedComment).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(expectedComment);
    }

    @DisplayName("изменить комментарий")
    @Test
    void update() {
        Comment expectedComment = em.find(Comment.class, COMMENT_ID);
        em.detach(expectedComment);

        Comment editedComment = new Comment();
        editedComment.setId(COMMENT_ID);
        editedComment.setText("new comment");

        Comment savedComment = repositoryJpa.save(editedComment);

        Comment actualComment = em.find(Comment.class, editedComment.getId());

        assertThat(actualComment).usingRecursiveComparison().ignoringCollectionOrder().isNotEqualTo(expectedComment);

        assertThat(savedComment).usingRecursiveComparison().ignoringCollectionOrder().isNotEqualTo(expectedComment);
    }

    @DisplayName("удалить комментарий")
    @Test
    void delete() {
        Comment comment = em.find(Comment.class, COMMENT_ID);
        assertNotNull(comment);
        em.detach(comment);

        repositoryJpa.delete(COMMENT_ID);

        Comment deletedComment = em.find(Comment.class, COMMENT_ID);
        assertNull(deletedComment);
    }
}
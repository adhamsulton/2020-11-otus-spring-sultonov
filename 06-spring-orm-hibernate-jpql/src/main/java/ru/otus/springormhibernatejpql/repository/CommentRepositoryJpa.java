package ru.otus.springormhibernatejpql.repository;

import org.springframework.stereotype.Repository;
import ru.otus.springormhibernatejpql.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> findAllByBookId(Long bookId) {
        TypedQuery<Comment> typedQuery = em.createQuery("select s from Comment s where s.book.id = :bookId", Comment.class);
        typedQuery.setParameter("bookId", bookId);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null || comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("delete from Comment where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

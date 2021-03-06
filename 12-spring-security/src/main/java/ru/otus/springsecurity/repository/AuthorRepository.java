package ru.otus.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springsecurity.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

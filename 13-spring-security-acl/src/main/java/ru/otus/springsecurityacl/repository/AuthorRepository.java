package ru.otus.springsecurityacl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springsecurityacl.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

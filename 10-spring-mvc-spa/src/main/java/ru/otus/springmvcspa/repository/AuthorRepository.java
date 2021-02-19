package ru.otus.springmvcspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springmvcspa.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

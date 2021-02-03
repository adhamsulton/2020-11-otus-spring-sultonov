package ru.otus.homework10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework10.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

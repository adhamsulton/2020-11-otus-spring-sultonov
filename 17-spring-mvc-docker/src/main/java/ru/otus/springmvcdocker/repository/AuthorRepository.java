package ru.otus.springmvcdocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springmvcdocker.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

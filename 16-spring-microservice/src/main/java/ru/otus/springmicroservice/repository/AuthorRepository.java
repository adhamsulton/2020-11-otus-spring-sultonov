package ru.otus.springmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springmicroservice.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

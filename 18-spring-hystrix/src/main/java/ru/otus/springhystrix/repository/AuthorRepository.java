package ru.otus.springhystrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springhystrix.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

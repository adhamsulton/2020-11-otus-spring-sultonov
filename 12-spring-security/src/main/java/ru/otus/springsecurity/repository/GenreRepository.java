package ru.otus.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springsecurity.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

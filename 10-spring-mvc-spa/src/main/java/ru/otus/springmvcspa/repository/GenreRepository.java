package ru.otus.springmvcspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springmvcspa.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

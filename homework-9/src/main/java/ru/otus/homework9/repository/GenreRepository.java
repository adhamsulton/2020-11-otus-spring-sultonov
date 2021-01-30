package ru.otus.homework9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework9.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

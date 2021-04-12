package ru.otus.springmvcdocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springmvcdocker.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

package ru.otus.springhystrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springhystrix.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

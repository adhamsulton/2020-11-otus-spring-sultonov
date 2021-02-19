package ru.otus.springmvcview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.springmvcview.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

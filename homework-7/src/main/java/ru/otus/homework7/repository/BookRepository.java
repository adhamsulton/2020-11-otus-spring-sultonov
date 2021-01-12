package ru.otus.homework7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework7.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

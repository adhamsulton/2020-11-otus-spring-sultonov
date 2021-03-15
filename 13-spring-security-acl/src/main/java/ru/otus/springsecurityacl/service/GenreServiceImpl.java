package ru.otus.springsecurityacl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springsecurityacl.domain.Genre;
import ru.otus.springsecurityacl.repository.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }
}

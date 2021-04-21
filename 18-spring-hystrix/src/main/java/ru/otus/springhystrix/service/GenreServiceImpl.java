package ru.otus.springhystrix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springhystrix.domain.Genre;
import ru.otus.springhystrix.repository.GenreRepository;

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

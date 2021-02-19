package ru.otus.springmvcspa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springmvcspa.domain.Author;
import ru.otus.springmvcspa.repository.AuthorRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }
}

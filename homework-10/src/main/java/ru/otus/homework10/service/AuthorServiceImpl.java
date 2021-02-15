package ru.otus.homework10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework10.domain.Author;
import ru.otus.homework10.repository.AuthorRepository;

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

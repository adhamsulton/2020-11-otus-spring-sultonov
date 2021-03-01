package ru.otus.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springsecurity.domain.Author;
import ru.otus.springsecurity.repository.AuthorRepository;

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

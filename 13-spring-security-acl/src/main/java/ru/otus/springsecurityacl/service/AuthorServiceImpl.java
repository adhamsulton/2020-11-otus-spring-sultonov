package ru.otus.springsecurityacl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springsecurityacl.domain.Author;
import ru.otus.springsecurityacl.repository.AuthorRepository;

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

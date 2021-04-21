package ru.otus.springhystrix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.springhystrix.domain.Author;
import ru.otus.springhystrix.repository.AuthorRepository;

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

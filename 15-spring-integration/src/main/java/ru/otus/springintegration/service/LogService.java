package ru.otus.springintegration.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.springintegration.domain.Log;
import ru.otus.springintegration.repository.LogRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogService {
    private final LogRepository repository;

    public void log(Log log) {
        repository.save(log);
    }
}

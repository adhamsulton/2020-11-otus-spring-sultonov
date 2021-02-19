package ru.otus.javabasedconfiguration.service;

import org.springframework.stereotype.Service;
import ru.otus.javabasedconfiguration.domain.User;

@Service
public class UserServiceImpl implements UserService {
    private final ReadWriteService readWriteService;

    public UserServiceImpl(ReadWriteService readWriteService) {
        this.readWriteService = readWriteService;
    }

    @Override
    public User getUser() {
        readWriteService.print("Please, enter your full name");
        return new User(readWriteService.read());
    }
}

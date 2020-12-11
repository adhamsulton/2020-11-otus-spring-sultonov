package ru.otus.homework2.service;

import org.springframework.stereotype.Service;
import ru.otus.homework2.domain.User;

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

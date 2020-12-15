package ru.otus.homework3.service;

import org.springframework.stereotype.Service;
import ru.otus.homework3.domain.User;

@Service
public class UserServiceImpl implements UserService {
    private final LocaleReadWriteService localeReadWriteService;
    private final ReadWriteService readWriteService;

    public UserServiceImpl(LocaleReadWriteService localeReadWriteService, ReadWriteService readWriteService) {
        this.localeReadWriteService = localeReadWriteService;
        this.readWriteService = readWriteService;
    }

    @Override
    public User getUser() {
        localeReadWriteService.printLocale("enter-name");
        return new User(readWriteService.read());
    }
}

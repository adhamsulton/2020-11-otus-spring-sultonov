package ru.otus.homework3.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework3.config.AppProps;
import ru.otus.homework3.domain.User;

@Service
public class UserServiceImpl implements UserService {
    private final ReadWriteService readWriteService;
    private final MessageSource messageSource;
    private final AppProps props;

    public UserServiceImpl(ReadWriteService readWriteService, MessageSource messageSource, AppProps props) {
        this.readWriteService = readWriteService;
        this.messageSource = messageSource;
        this.props = props;
    }

    @Override
    public User getUser() {
        readWriteService.print(messageSource.getMessage("enter-name", new String[]{}, props.getLocale()));
        return new User(readWriteService.read());
    }
}

package ru.otus.homework3.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework3.config.AppProps;

@Service
public class LocaleServiceImpl implements LocaleService {
    private final MessageSource messageSource;
    private final AppProps props;

    public LocaleServiceImpl(MessageSource messageSource, AppProps props) {
        this.messageSource = messageSource;
        this.props = props;
    }

    @Override
    public String locale(String alias, String... args) {
        return messageSource.getMessage(alias, args, props.getLocale());
    }
}

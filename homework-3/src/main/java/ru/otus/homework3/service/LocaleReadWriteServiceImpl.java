package ru.otus.homework3.service;

import org.springframework.stereotype.Service;

@Service
public class LocaleReadWriteServiceImpl implements LocaleReadWriteService {
    private final LocaleService localeService;
    private final ReadWriteService readWriteService;

    public LocaleReadWriteServiceImpl(LocaleService localeService, ReadWriteService readWriteService) {
        this.localeService = localeService;
        this.readWriteService = readWriteService;
    }

    @Override
    public void printLocale(String alias, String... args) {
        readWriteService.print(localeService.locale(alias, args));
    }
}

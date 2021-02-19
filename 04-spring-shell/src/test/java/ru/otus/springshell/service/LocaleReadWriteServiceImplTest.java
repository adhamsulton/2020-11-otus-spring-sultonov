package ru.otus.springshell.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.AdditionalMatchers.and;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocaleReadWriteServiceImplTest {
    @InjectMocks
    private LocaleReadWriteServiceImpl service;
    @Mock
    private LocaleService localeService;
    @Mock
    private ReadWriteService readWriteService;

    @Test
    void printLocale() {
        when(localeService.locale(anyString(), eq("User"))).thenReturn("hello User");

        service.printLocale("welcome-ee", "User");

        verify(readWriteService).print(and(notNull(), argThat(s -> s.endsWith("User"))));
    }
}

package ru.otus.springshell.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.springshell.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private LocaleReadWriteService localeService;
    @Mock
    private ReadWriteService readWriteService;

    @Test
    void getUser() {
        when(readWriteService.read()).thenReturn("something");

        User actualUser = service.getUser();

        assertNotNull(actualUser);
        assertEquals("something", actualUser.getFullName());
        verify(localeService, times(1)).printLocale(anyString());
    }
}
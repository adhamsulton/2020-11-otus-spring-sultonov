package ru.otus.homework4.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework4.domain.User;
import ru.otus.homework4.events.EventsPublisher;
import ru.otus.homework4.service.LocaleService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final LocaleService localeService;
    private final EventsPublisher eventsPublisher;

    private User user;

    @ShellMethod(value = "Enter full name command", key = {"e", "enter", "enterName"})
    public String enterUserFullName(@ShellOption(defaultValue = "User") String userFullName) {
        this.user = new User(userFullName);
        return localeService.locale("welcome-test", user.getFullName());
    }

    @ShellMethod(value = "Start test command", key = {"s", "start", "start-test"})
    public void startTest() {
        eventsPublisher.publish(user);
    }
}

package ru.otus.springintegration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.springintegration.domain.Log;

@MessagingGateway
public interface Logger {
    @Gateway(requestChannel = "logsChannel")
    void process(Log log);
}

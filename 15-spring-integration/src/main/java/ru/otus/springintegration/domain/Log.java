package ru.otus.springintegration.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("logs")
public class Log {
    @Id
    private String id;
    private Long userId;
    private String request;

    public Log(Long userId, String request) {
        this.userId = userId;
        this.request = request;
    }
}

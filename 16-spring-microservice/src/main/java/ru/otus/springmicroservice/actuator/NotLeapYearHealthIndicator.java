package ru.otus.springmicroservice.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Year;

@Slf4j
@Component
public class NotLeapYearHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (Year.now().isLeap()) {
            log.info("leap :{}", LocalDateTime.now());
            return Health.down().build();
        } else {
            log.info("not leap :{}", LocalDateTime.now());
            return Health.up().build();
        }
    }
}

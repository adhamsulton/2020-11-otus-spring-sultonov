package ru.otus.homework11.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import ru.otus.homework11.domain.Book;

@DataMongoTest
class BookControllerTest {
    @Autowired
    private RouterFunction routerFunction;

    @Test
    void fetch() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();

        client.get()
                .uri("/fe/books")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void save() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();

        client.post()
                .uri("/fe/books")
                .bodyValue(new Book())
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void get() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();

        client.get()
                .uri("/fe/books/1")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void delete() {
        WebTestClient client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();

        client.delete()
                .uri("/fe/books/1")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
package ru.otus.homework11.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.otus.homework11.domain.Book;
import ru.otus.homework11.repository.BookRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class Route {

    @Bean
    public RouterFunction<ServerResponse> routes(BookRepository repository) {
        return route()
                .GET("/fe/books", accept(APPLICATION_JSON),
                        serverRequest -> ok().contentType(APPLICATION_JSON)
                                .body(repository.findAll(), Book.class))
                .GET("/fe/books/{id}", accept(APPLICATION_JSON),
                        serverRequest -> ok().contentType(APPLICATION_JSON)
                                .body(repository.findById(serverRequest.pathVariable("id")), Book.class))
                .POST("/fe/books", accept(APPLICATION_JSON),
                        serverRequest -> serverRequest.bodyToMono(Book.class)
                                .map(repository::save)
                                .flatMap(result -> ok().body(result, Book.class)))
                .DELETE("/fe/books/{id}", accept(APPLICATION_JSON),
                        serverRequest -> ok().contentType(APPLICATION_JSON)
                                .body(repository.deleteById(serverRequest.pathVariable("id")), Book.class))
                .build();
    }

}

package ru.otus.homework9.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.homework9.domain.Author;
import ru.otus.homework9.domain.Book;
import ru.otus.homework9.domain.Genre;
import ru.otus.homework9.service.AuthorService;
import ru.otus.homework9.service.BookService;
import ru.otus.homework9.service.CommentService;
import ru.otus.homework9.service.GenreService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
    private static final Long BOOK_ID = 2L;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private CommentService commentService;

    @SneakyThrows
    @Test
    void getList() {
        List<Book> expectedBooks = List.of(
                new Book(2L, "new book",
                        List.of(new Author(2L, "Стивен Кинг")),
                        new Genre(2L, "Детектив")
                ));

        given(bookService.findAll()).willReturn(expectedBooks);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", equalTo(expectedBooks)));
    }

    @SneakyThrows
    @Test
    void view() {
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book",
                List.of(new Author(2L, "Стивен Кинг")),
                new Genre(2L, "Детектив")
        ));

        given(bookService.findById(anyLong())).willReturn(expectedBook);

        mvc.perform(get("/books/view/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", equalTo(expectedBook.get())));
    }

    @SneakyThrows
    @Test
    void editPage() {
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book",
                List.of(new Author(2L, "Стивен Кинг")),
                new Genre(2L, "Детектив")
        ));

        given(bookService.findById(anyLong())).willReturn(expectedBook);
        given(genreService.findAll()).willReturn(List.of(expectedBook.get().getGenre()));
        given(authorService.findAll()).willReturn(expectedBook.get().getAuthorList());

        mvc.perform(get("/books/edit/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", equalTo(expectedBook.get())))
                .andExpect(model().attribute("genres", equalTo(List.of(expectedBook.get().getGenre()))))
                .andExpect(model().attribute("authors", equalTo(expectedBook.get().getAuthorList())));
    }

    @SneakyThrows
    @Test
    void save() {
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book",
                List.of(new Author(2L, "Стивен Кинг")),
                new Genre(2L, "Детектив")
        ));

        mvc.perform(post("/books/save").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(expectedBook.get())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @SneakyThrows
    @Test
    void delete() {
        mvc.perform(MockMvcRequestBuilders.delete("/books/delete/" + BOOK_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

}
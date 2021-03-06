package ru.otus.springsecurityacl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.test.context.TestSecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.springsecurityacl.config.AclMethodSecurityConfiguration;
import ru.otus.springsecurityacl.controller.BookController;
import ru.otus.springsecurityacl.domain.Author;
import ru.otus.springsecurityacl.domain.Book;
import ru.otus.springsecurityacl.domain.Genre;
import ru.otus.springsecurityacl.service.*;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({AclMethodSecurityConfiguration.class})
@WebMvcTest(BookController.class)
class BookControllerTest {
    private static final Long BOOK_ID = 2L;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @MockBean
    private BookService bookService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private CommentService commentService;
    @MockBean
    private MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler;


    @SneakyThrows
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN"}
    )
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
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN"}
    )
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
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN"}
    )
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
    @WithMockUser(
            username = "user",
            roles = {"USER"}
    )
    @Test
    void editPageByUser() {
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book",
                List.of(new Author(2L, "Стивен Кинг")),
                new Genre(2L, "Детектив")
        ));

        given(bookService.findById(anyLong())).willReturn(expectedBook);
        given(genreService.findAll()).willReturn(List.of(expectedBook.get().getGenre()));
        given(authorService.findAll()).willReturn(expectedBook.get().getAuthorList());
        System.out.println(TestSecurityContextHolder.getContext());
        mvc.perform(get("/books/edit/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sorry, your access is refused due to security " +
                        "reasons of our server and also our sensitive data.")));
    }

    @SneakyThrows
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN"}
    )
    @Test
    void save() {
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book",
                List.of(new Author(2L, "Стивен Кинг")),
                new Genre(2L, "Детектив")
        ));

        mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(expectedBook.get())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @SneakyThrows
    @WithMockUser(
            username = "user",
            roles = {"USER"}
    )
    @Test
    void saveByUser() {
        Optional<Book> expectedBook = Optional.of(new Book(BOOK_ID, "new book",
                List.of(new Author(2L, "Стивен Кинг")),
                new Genre(2L, "Детектив")
        ));

        mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sorry, your access is refused due to security " +
                        "reasons of our server and also our sensitive data.")));
    }

    @SneakyThrows
    @WithMockUser(
            username = "admin",
            roles = {"ADMIN"}
    )
    @Test
    void delete() {
        mvc.perform(MockMvcRequestBuilders.delete("/books/" + BOOK_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @SneakyThrows
    @WithMockUser(
            username = "user",
            roles = {"USER"}
    )
    @Test
    void deleteByUser() {
        mvc.perform(MockMvcRequestBuilders.delete("/books/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sorry, your access is refused due to security " +
                        "reasons of our server and also our sensitive data.")));
    }
}
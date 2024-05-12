package ru.mts.springaop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.mts.springaop.controller.BookController;
import ru.mts.springaop.entity.Book;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringAopApplicationTests {

    @Autowired
    private WebTestClient client;

    @Test
    void getMethod() {
        List<Book> responseBody = client.get()
                .uri("/api/books/all")
                .exchange()
                .expectBodyList(Book.class)
                .returnResult()
                .getResponseBody();
    }

    @Test
    void create() {
        Book book = new Book();
        book.setAuthor("Новый автор");
        book.setName("Новое имя");
        book.setPrice(100);
        book.setCategory("Категория");
        book.setPublication("П");
        book.setPages(250);
        book.setId(100);

        client.post()
                .uri("/api/books/create")
                .bodyValue(book)
                .exchange()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
    }

}

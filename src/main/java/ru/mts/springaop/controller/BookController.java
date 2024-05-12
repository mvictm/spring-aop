package ru.mts.springaop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.mts.springaop.entity.Book;
import ru.mts.springaop.service.BookService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return service.getBooks();
    }

    @GetMapping("/get/{id}")
    public Book getBookById(@PathVariable("id") int id) {
        return service.getBook(id);
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public Book deleteBook(@PathVariable("id") int id) {
        return service.deleteBook(id);
    }
}

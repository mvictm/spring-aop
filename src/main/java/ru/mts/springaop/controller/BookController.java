package ru.mts.springaop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.mts.springaop.entity.Book;
import ru.mts.springaop.exceptions.DuplicateRecordException;
import ru.mts.springaop.service.BookService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/books")
    public List<Book> getBooks() {
        log.info("Запрос на получения списка книг");
        return service.getBooks();
    }

    @GetMapping("/books/resource")
    public Book getBookByName(@RequestParam("name") String name) {
        return service.findByNameBook(name);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") int id) {
        return service.getBook(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        if (service.existBook(book)) {
            throw new DuplicateRecordException(book);
        }
        return service.createBook(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        if (service.existBook(book)) throw new DuplicateRecordException(book);
        return service.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public Book deleteBook(@PathVariable("id") int id) {
        return service.deleteBook(id);
    }
}

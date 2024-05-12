package ru.mts.springaop.service;

import ru.mts.springaop.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Book createBook(Book book);

    Book updateBook(int bookId, Book book);

    Book getBook(int bookId);

    Book deleteBook(int bookId);

    Book findByNameBook(String name);

    boolean existBook(Book book);
}

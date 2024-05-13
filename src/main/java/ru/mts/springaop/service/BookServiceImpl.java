package ru.mts.springaop.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.mts.springaop.annotations.Logging;
import ru.mts.springaop.annotations.TimeMetric;
import ru.mts.springaop.entity.Book;
import ru.mts.springaop.exceptions.ResourceNotFoundException;
import ru.mts.springaop.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    @Logging
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    @TimeMetric
    @Logging(name = "Создание книги")
    public Book createBook(Book book) {
        if (existBook(book)) {
            throw new IllegalArgumentException();
        }
        return bookRepository.save(book);
    }

    @Override
    public Book getBook(int bookId) {
        return Optional.of(bookId)
                .filter(id -> id > 0)
                .flatMap(bookRepository::findById)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
    }

    @Override
    public Book deleteBook(int bookId) {
        Book bookFromDB = Optional.of(bookId)
                .filter(id -> id > 0)
                .flatMap(bookRepository::findById)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        bookRepository.delete(bookFromDB);

        return bookFromDB;
    }

    @Override
    public boolean existBook(Book book) {
        if (book == null || StringUtils.isAnyBlank(book.getName(), book.getAuthor(), book.getPublication())) {
            throw new IllegalArgumentException();
        }
        return bookRepository.existsByName(book.getName())
                && bookRepository.existsByAuthor(book.getAuthor())
                && bookRepository.existsByPublication(book.getPublication());
    }
}

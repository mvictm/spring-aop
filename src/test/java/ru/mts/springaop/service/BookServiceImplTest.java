package ru.mts.springaop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mts.springaop.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void getBooks() {
    }

    @Test
    void createBook() {
    }

    @Test
    void getBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void existBook() {
    }
}
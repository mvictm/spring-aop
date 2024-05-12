package ru.mts.springaop.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.mts.springaop.entity.Book;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateRecordException extends RuntimeException {

    public DuplicateRecordException(Book book) {
        super(String.format("A book titled '%s', by '%s', published by '%s' already exists.",
                book.getName(), book.getAuthor(), book.getPublication()));
    }
}

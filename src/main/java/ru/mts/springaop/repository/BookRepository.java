package ru.mts.springaop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.springaop.entity.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByName(String name);
    boolean existsByName(String name);
    boolean existsByAuthor(String author);
    boolean existsByPublication(String publication);
}

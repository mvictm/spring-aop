package ru.mts.springaop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.springaop.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByName(String name);

    boolean existsByAuthor(String author);

    boolean existsByPublication(String publication);
}

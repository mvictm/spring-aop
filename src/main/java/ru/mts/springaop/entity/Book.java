package ru.mts.springaop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "publication", nullable = false)
    private String publication;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "pages", nullable = false)
    private int pages;

    @Column(name = "price", nullable = false)
    private int price;
}

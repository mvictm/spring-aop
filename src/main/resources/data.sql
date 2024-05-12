CREATE TABLE mts.book
(
    id          int,
    author      varchar(255) NOT NULL,
    category    varchar(255) NOT NULL,
    name        varchar(255) NOT NULL,
    pages       int          NOT NULL,
    price       int          NOT NULL,
    publication varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO mts.book (id, author, category, name, pages, price, publication)
VALUES (1, 'Charles Dickens', 'essays', 'Fletcher and Webster', 100, 550, 'FR Publication'),
       (2, 'George Eliot', 'journalism', 'Victorian era', 500, 200, 'US Publication'),
       (3, 'George Orwell', 'novels', 'Animal Farm', 700, 240, 'UK Publication');
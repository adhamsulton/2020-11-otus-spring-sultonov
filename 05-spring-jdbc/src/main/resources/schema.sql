DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS
(
    ID        BIGINT PRIMARY KEY,
    FULL_NAME VARCHAR(255)
);

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);

CREATE SEQUENCE BOOKS_SQ START WITH 3;

DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS
(
    ID       BIGINT DEFAULT BOOKS_SQ.nextval PRIMARY KEY,
    NAME     VARCHAR(255),
    GENRE_ID BIGINT,
    FOREIGN KEY (GENRE_ID) REFERENCES GENRES (ID)
);

DROP TABLE IF EXISTS BOOK_AUTHORS;
CREATE TABLE BOOK_AUTHORS
(
    BOOK_ID   BIGINT,
    AUTHOR_ID BIGINT,
    FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID) ON DELETE CASCADE,
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS (ID),
    UNIQUE KEY BOOK_AUTHORS_U1 (BOOK_ID, AUTHOR_ID)
);
package com.organicautonomy.reviewapi.util.feign;

import com.organicautonomy.reviewapi.dto.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookClientTest {
    public final Book TO_SAVE = new Book("Holes", "Louis Sachar", LocalDate.of(1998, 11, 1));
    public final Book HOLES = new Book(1, "Holes", "Louis Sachar", LocalDate.of(1998, 11, 1));
    public final Book THE_PRINCE = new Book(2,"The Prince", "Louis Sachar", LocalDate.of(1999, 12, 1));

    @MockBean
    private BookClient client;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createBook() {
        when(client.createBook(TO_SAVE)).thenReturn(HOLES);

        Book book = client.createBook(TO_SAVE);
        assertEquals(book, HOLES);
    }

    @Test
    void getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(HOLES);
        books.add(THE_PRINCE);

        when(client.getAllBooks()).thenReturn(books);

        List<Book> fromClient = client.getAllBooks();
        assertEquals(2, fromClient.size());
    }

    @Test
    void getBookById() {
        when(client.getBookById(HOLES.getId())).thenReturn(HOLES);

        Book fromClient = client.getBookById(HOLES.getId());

        assertEquals(HOLES, fromClient);
    }

    @Test
    void updateBook() {
        doNothing().when(client).updateBook(THE_PRINCE.getId(), THE_PRINCE);
    }

    @Test
    void deleteBook() {
        doNothing().when(client).deleteBook(THE_PRINCE.getId());
    }

    @Test
    void getBookByTitle() {
        when(client.getBookByTitle(HOLES.getTitle())).thenReturn(HOLES);

        Book book = client.getBookByTitle(HOLES.getTitle());

        assertEquals(HOLES, book);

    }

    @Test
    void getBooksByReleaseDate() {
        List<Book> books = new ArrayList<>();
        books.add(THE_PRINCE);

        when(client.getBooksByReleaseDate(THE_PRINCE.getReleaseDate())).thenReturn(books);
    }

    @Test
    void getBooksByAuthor() {
        List<Book> books = new ArrayList<>();
        books.add(HOLES);

        when(client.getBooksByAuthor(HOLES.getAuthor())).thenReturn(books);
    }
}
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
    public final Book INVALID = new Book();

    @MockBean
    private BookClient bookClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createBook() {
        when(bookClient.createBook(TO_SAVE)).thenReturn(HOLES);

        Book book = bookClient.createBook(TO_SAVE);
        assertEquals(book, HOLES);
    }

    @Test
    void getAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(HOLES);
        books.add(THE_PRINCE);

        when(bookClient.getAllBooks()).thenReturn(books);

        List<Book> fromClient = bookClient.getAllBooks();
        assertEquals(2, fromClient.size());
    }

    @Test
    void getBookById() {
        when(bookClient.getBookById(HOLES.getId())).thenReturn(HOLES);

        Book fromClient = bookClient.getBookById(HOLES.getId());

        assertEquals(HOLES, fromClient);
    }

    @Test
    void updateBook() {
        doNothing().when(bookClient).updateBook(THE_PRINCE.getId(), THE_PRINCE);
    }

    @Test
    void deleteBook() {
        doNothing().when(bookClient).deleteBook(THE_PRINCE.getId());
    }

    @Test
    void getBookByTitle() {
        when(bookClient.getBookByTitle(HOLES.getTitle())).thenReturn(HOLES);
    }

    @Test
    void getBooksByReleaseDate() {
        List<Book> books = new ArrayList<>();
        books.add(THE_PRINCE);
        when(bookClient.getBooksByReleaseDate(THE_PRINCE.getReleaseDate())).thenReturn(books);
    }

    @Test
    void getBooksByAuthor() {
        List<Book> books = new ArrayList<>();
        books.add(HOLES);

        when(bookClient.getBooksByAuthor(HOLES.getAuthor())).thenReturn(books);
    }
}
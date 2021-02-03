package com.organicautonomy.bookservice.dao;

import com.organicautonomy.bookservice.dto.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {
    @Autowired
    private BookRepository repository;
    private Book holes, thePrince;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        holes = new Book("Holes", "Louis Sachar", LocalDate.of(1998, 11, 1));
        thePrince = new Book("The Prince", "Niccolo Machiavelli", LocalDate.of(1991, 11,1));
    }

    @Test
    void saveFindBook() {
        holes = repository.save(holes);
        Optional<Book> fromRepository = repository.findById(holes.getId());

        assertEquals(fromRepository.get(), holes);
    }

    @Test
    void findBooksByReleaseDate() {
        thePrince = repository.save(thePrince);
        List<Book> books = repository.findBooksByReleaseDate(LocalDate.of(1532, 11, 1));

        assertEquals(1, books.size());
    }

    @Test
    void findBookByTitle() {
        thePrince = repository.save(thePrince);
        Book fromRepository = repository.findBookByTitle(thePrince.getTitle());

        assertEquals(fromRepository, thePrince);
    }
}
package com.organicautonomy.bookservice.controller;

import com.organicautonomy.bookservice.dao.BookRepository;
import com.organicautonomy.bookservice.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody @Valid Book book) {
        return repository.save(book);
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookByTitle(@PathVariable String title) {
        Book book = repository.findBookByTitle(title);

        if (book == null) throw new NoSuchElementException();

        return book;
    }

    @GetMapping("/date/{releaseDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getBooksByReleaseDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate) {
        List<Book> books = repository.findBooksByReleaseDate(releaseDate);

        if (books.size() == 0) throw new NoSuchElementException();

        return books;
    }
}

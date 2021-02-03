package com.organicautonomy.bookservice.controller;

import com.organicautonomy.bookservice.dao.BookRepository;
import com.organicautonomy.bookservice.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

}

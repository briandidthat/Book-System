package com.organicautonomy.reviewapi.util.feign;

import com.organicautonomy.reviewapi.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "book-service")
@RequestMapping(value = "/books")
public interface BookClient {
    @PostMapping
    Book createBook(@RequestBody @Valid Book book);

    @GetMapping
    List<Book> getAllBooks();

    @GetMapping("/{bookId}")
    Book getBookById(@PathVariable Integer bookId);

    @PutMapping("/{bookId}")
    void updateBook(@PathVariable Integer bookId, @RequestBody @Valid Book book);

    @DeleteMapping("/{bookId}")
    void deleteBook(@PathVariable Integer bookId);

    @GetMapping("/title/{title}")
    Book getBookByTitle(@PathVariable String title);

    @GetMapping("/date/{releaseDate}")
    List<Book> getBooksByReleaseDate(@PathVariable LocalDate releaseDate);

    @GetMapping("/authors/{author}")
    List<Book> getBooksByAuthor(@PathVariable String author);
}

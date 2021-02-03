package com.organicautonomy.bookservice.dao;

import com.organicautonomy.bookservice.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByReleaseDate(LocalDate releaseDate);
    Book findBookByTitle(String title);
}

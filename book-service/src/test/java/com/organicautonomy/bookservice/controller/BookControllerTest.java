package com.organicautonomy.bookservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organicautonomy.bookservice.dao.BookRepository;
import com.organicautonomy.bookservice.dto.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {
    public final Book TO_SAVE = new Book("Holes", "Louis Sachar", LocalDate.of(1998, 11, 1));
    public final Book HOLES = new Book(1, "Holes", "Louis Sachar", LocalDate.of(1998, 11, 1));
    public final Book THE_PRINCE = new Book("The Prince", "Louis Sachar", LocalDate.of(1999, 12, 1));

    @MockBean
    private BookRepository repository;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(HOLES);
        books.add(THE_PRINCE);

        when(repository.findAll()).thenReturn(books);

        String outputJson = mapper.writeValueAsString(books);

        this.mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }
}
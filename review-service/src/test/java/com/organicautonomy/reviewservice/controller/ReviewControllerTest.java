package com.organicautonomy.reviewservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organicautonomy.reviewservice.dao.ReviewRepository;
import com.organicautonomy.reviewservice.dto.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
    private final Review TO_SAVE = new Review(1, 1, new BigDecimal("3.10"), "Ehh, not horrible.");
    private final Review REVIEW1 = new Review(1,1, 1, new BigDecimal("3.10"), "Ehh, not horrible.");
    private final Review REVIEW2 = new Review(2, 2, 2, new BigDecimal("4.80"), "Great book!");

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllReviews() throws Exception {
        List<Review> reviews = new ArrayList<>();
        reviews.add(REVIEW1);
        reviews.add(REVIEW2);

        when(repository.findAll()).thenReturn(reviews);

        String outputJson = mapper.writeValueAsString(reviews);

        this.mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }

    @Test
    void testCreateReview() throws Exception {
        String inputJson = mapper.writeValueAsString(TO_SAVE);
        String outputJson = mapper.writeValueAsString(REVIEW1);

        when(repository.save(TO_SAVE)).thenReturn(REVIEW1);

        this.mockMvc.perform(post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void testGetReviewsByBookId() throws Exception {
        List<Review> reviews = new ArrayList<>();
        reviews.add(REVIEW1);

        when(repository.findReviewsByBookId(REVIEW1.getBookId())).thenReturn(reviews);

        String outputJson = mapper.writeValueAsString(reviews);

        this.mockMvc.perform(get("/reviews/books/" + REVIEW1.getBookId()))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson))
                .andDo(print());
    }
}
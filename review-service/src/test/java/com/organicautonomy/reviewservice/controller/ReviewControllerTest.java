package com.organicautonomy.reviewservice.controller;

import com.organicautonomy.reviewservice.dto.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
    private final Review TO_SAVE = new Review(1, 1, new BigDecimal("3.10"), "Ehh, not horrible.");
    private final Review REVIEW1 = new Review(1, 1, new BigDecimal("3.10"), "Ehh, not horrible.");

    @BeforeEach
    void setUp() {
    }
}
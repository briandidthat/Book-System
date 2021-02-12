package com.organicautonomy.reviewapi.util.feign;

import com.organicautonomy.reviewapi.dto.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ReviewClientTest {
    @Autowired
    private ReviewClient reviewClient;
    private final Review TO_SAVE = new Review(1, 10, new BigDecimal("2.4"), "Trash.");
    private final Review REVIEW1 = new Review(1,1, 10, new BigDecimal("2.4"), "Trash.");
    private final Review REVIEW2 = new Review(3,2, 12, new BigDecimal("4.5"), "Amazing.");

    @Test
    void createReview() {
    }

    @Test
    void getAllReviews() {
    }

    @Test
    void getReviewById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getReviewsByBookId() {
    }

    @Test
    void getReviewsByUserID() {
    }

    @Test
    void getReviewsByRating() {
    }
}
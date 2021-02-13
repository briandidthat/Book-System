package com.organicautonomy.reviewapi.util.feign;

import com.organicautonomy.reviewapi.dto.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReviewClientTest {
    private final Review TO_SAVE = new Review(1, 10, new BigDecimal("2.4"), "Trash.");
    private final Review REVIEW1 = new Review(1,1, 10, new BigDecimal("2.4"), "Trash.");
    private final Review REVIEW2 = new Review(3,2, 12, new BigDecimal("4.5"), "Amazing.");

    @MockBean
    private ReviewClient client;

    @Test
    void createReview() {
        when(client.createReview(TO_SAVE)).thenReturn(REVIEW1);

        Review fromClient = client.createReview(TO_SAVE);

        assertEquals(REVIEW1, fromClient);
    }

    @Test
    void getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(REVIEW1);
        reviews.add(REVIEW2);

        when(client.getAllReviews()).thenReturn(reviews);

        List<Review> fromClient = client.getAllReviews();
        assertEquals(2, fromClient.size());
    }

    @Test
    void getReviewById() {
        when(client.getReviewById(REVIEW1.getId())).thenReturn(REVIEW1);


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
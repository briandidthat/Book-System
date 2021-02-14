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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReviewClientTest {
    private final Review TO_SAVE = new Review(1, 10, new BigDecimal("2.4"), "Trash.");
    private final Review REVIEW1 = new Review(1,1, 10, new BigDecimal("2.4"), "Trash.");
    private final Review REVIEW2 = new Review(3,2, 12, new BigDecimal("4.5"), "Amazing.");
    private final Review REVIEW3 = new Review(4,3, 12, new BigDecimal("2.3"), "No Good.");

    @MockBean
    private ReviewClient client;

    @Test
    void testCreateReview() {
        when(client.createReview(TO_SAVE)).thenReturn(REVIEW1);

        Review fromClient = client.createReview(TO_SAVE);

        assertEquals(REVIEW1, fromClient);
    }

    @Test
    void testGetAllReviews() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(REVIEW1);
        reviews.add(REVIEW2);

        when(client.getAllReviews()).thenReturn(reviews);

        List<Review> fromClient = client.getAllReviews();
        assertEquals(2, fromClient.size());
    }

    @Test
    void testGetReviewById() {
        when(client.getReviewById(REVIEW1.getId())).thenReturn(REVIEW1);

        Review fromClient = client.getReviewById(REVIEW1.getId());

        assertEquals(REVIEW1, fromClient);
    }

    @Test
    void testUpdateReview() {
        doNothing().when(client).updateReview(REVIEW2.getId(), REVIEW2);
    }

    @Test
    void testDeleteReview() {
        doNothing().when(client).deleteReview(REVIEW1.getId());
    }

    @Test
    void testGetReviewsByBookId() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(REVIEW2);
        reviews.add(REVIEW3);

        when(client.getReviewsByBookId(REVIEW2.getBookId())).thenReturn(reviews);

        List<Review> fromClient = client.getReviewsByBookId(REVIEW2.getBookId());

        assertEquals(2, fromClient.size());
    }

    @Test
    void testGetReviewsByUserId() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(REVIEW1);

        when(client.getReviewsByUserId(REVIEW1.getUserId())).thenReturn(reviews);

        List<Review> fromClient = client.getReviewsByUserId(REVIEW1.getUserId());
        assertEquals(1, fromClient.size());
    }

    @Test
    void testGetReviewsByRating() {
    }
}
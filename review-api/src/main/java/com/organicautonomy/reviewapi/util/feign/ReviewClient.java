package com.organicautonomy.reviewapi.util.feign;

import com.organicautonomy.reviewapi.dto.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "review-service")
@RequestMapping("/reviews")
public interface ReviewClient {
    @PostMapping
    Review createReview();
    @GetMapping
    List<Review> getAllReviews();
    @GetMapping("/{reviewId}")
    Review getReviewById();
    @PutMapping("/{reviewId}")
    void updateUser(@PathVariable Integer reviewId, @RequestBody Review review);
    @DeleteMapping("/{reviewId}")
    void deleteUser(@PathVariable Integer reviewId);
    @GetMapping("/books/{bookId}")
    List<Review> getReviewsByBookId(@PathVariable Integer bookId);
    @GetMapping("/user/{userId}")
    List<Review> getReviewsByUserID(@PathVariable Integer userId);
    @GetMapping("/ratings/{rating}")
    List<Review> getReviewsByRating(@PathVariable Integer rating);
}

package com.organicautonomy.reviewapi.util.feign;

import com.organicautonomy.reviewapi.dto.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "review-service")
@RequestMapping(value = "/reviews")
public interface ReviewClient {
    @PostMapping
    Review createReview(@RequestBody @Valid Review review);

    @GetMapping
    List<Review> getAllReviews();

    @GetMapping("/{reviewId}")
    Review getReviewById(@PathVariable Integer reviewId);

    @PutMapping("/{reviewId}")
    void updateReview(@PathVariable Integer reviewId, @RequestBody Review review);

    @DeleteMapping("/{reviewId}")
    void deleteReview(@PathVariable Integer reviewId);

    @GetMapping("/books/{bookId}")
    List<Review> getReviewsByBookId(@PathVariable Integer bookId);

    @GetMapping("/user/{userId}")
    List<Review> getReviewsByUserId(@PathVariable Integer userId);

    @GetMapping("/ratings/{rating}")
    List<Review> getReviewsByRating(@PathVariable Integer rating);
}

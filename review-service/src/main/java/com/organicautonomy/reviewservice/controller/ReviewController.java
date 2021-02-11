package com.organicautonomy.reviewservice.controller;

import com.organicautonomy.reviewservice.dao.ReviewRepository;
import com.organicautonomy.reviewservice.dto.Review;
import com.organicautonomy.reviewservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@RequestBody @Valid Review review) {
        return repository.save(review);
    }

    @GetMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public Review getReviewById(@PathVariable Integer reviewId) {
        Optional<Review> review = repository.findById(reviewId);

        return review.orElseThrow(() -> new ResourceNotFoundException("There are no reviews associated with the id provided."));
    }

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReview(@PathVariable Integer reviewId, @RequestBody @Valid Review review) {
        if (!reviewId.equals(review.getBookId())) {
            throw new IllegalArgumentException("Path id must match review object id.");
        }

        Optional<Review> compare = repository.findById(reviewId);

        if (!compare.isPresent()) {
            throw new ResourceNotFoundException("There are no reviews associated with the id provided.");
        }

        // if we successfully arrive here, the path and object have same id and object exists in db.
        repository.save(review);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Integer reviewId) {
        Optional<Review> review = repository.findById(reviewId);

        if (!review.isPresent()) {
            throw new ResourceNotFoundException("There are no reviews associated with the id provided.");
        }
        // if we successfully arrive here, the object exists in db and we can safely delete it.
        repository.delete(review.get());
    }

    @GetMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsByBookId(@PathVariable Integer bookId) {
        List<Review> reviews = repository.findReviewsByBookId(bookId);

        if (reviews.size() == 0) {
            throw new ResourceNotFoundException("There are no reviews associated with the book id provided.");
        }

        return reviews;
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsByUserId(@PathVariable Integer userId) {
        List<Review> reviews = repository.findReviewsByUserId(userId);

        if (reviews.size() == 0) {
            throw new ResourceNotFoundException("There are no reviews associated with the user id provided.");
        }

        return reviews;
    }

    @GetMapping("/ratings/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsByRating(@PathVariable Integer rating) {
        List<Review> reviews = repository.findReviewsByRating(rating);

        if (reviews.size() == 0) {
            throw new ResourceNotFoundException("There are no reviews with the rating provided.");
        }

        return reviews;
    }

}

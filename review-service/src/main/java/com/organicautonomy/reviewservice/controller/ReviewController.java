package com.organicautonomy.reviewservice.controller;

import com.organicautonomy.reviewservice.dao.ReviewRepository;
import com.organicautonomy.reviewservice.dto.Review;
import com.organicautonomy.reviewservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

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

    @GetMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsByBookId(@PathVariable Integer bookId) {
        List<Review> reviews = repository.findReviewsByBookId(bookId);

        if (reviews.size() == 0) throw new NoSuchElementException();

        return reviews;
    }

    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsByUserId(@PathVariable Integer userId) {
        List<Review> reviews = repository.findReviewsByUserId(userId);

        if (reviews.size() == 0) throw new ResourceNotFoundException("No reviews associated with the id: " + userId);

        return reviews;
    }

    @GetMapping("/ratings/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Review> getReviewsByRating(@PathVariable Integer rating) {
        List<Review> reviews = repository.findReviewsByRating(rating);

        if (reviews.size() == 0) throw new ResourceNotFoundException("There are no reviews with that rating.");

        return reviews;
    }

}

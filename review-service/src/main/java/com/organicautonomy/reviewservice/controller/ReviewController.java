package com.organicautonomy.reviewservice.controller;

import com.organicautonomy.reviewservice.dao.ReviewRepository;
import com.organicautonomy.reviewservice.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}

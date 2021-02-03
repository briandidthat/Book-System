package com.organicautonomy.reviewservice.controller;

import com.organicautonomy.reviewservice.dao.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired
    private ReviewRepository repository;



}

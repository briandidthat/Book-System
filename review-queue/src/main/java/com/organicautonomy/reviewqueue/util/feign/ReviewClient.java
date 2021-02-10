package com.organicautonomy.reviewqueue.util.feign;

import com.organicautonomy.reviewqueue.util.message.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value = "/reviews")
@FeignClient(name = "review-service")
public interface ReviewClient {
    @PostMapping
    public Review createReview(@RequestBody @Valid Review review);
}

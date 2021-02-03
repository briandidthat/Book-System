package com.organicautonomy.reviewservice.dao;

import com.organicautonomy.reviewservice.dto.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findReviewsByBookId(int bookId);
    List<Review> findReviewsByUserId(int userId);
    @Query(value = "SELECT * FROM review WHERE rating > ?1", nativeQuery = true)
    List<Review> findReviewsByRating(int rating);
}

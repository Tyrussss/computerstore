package com.cs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.model.Review;

@Service
public class ReviewService {
	@Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Review review) {
        reviewRepository.insert(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}

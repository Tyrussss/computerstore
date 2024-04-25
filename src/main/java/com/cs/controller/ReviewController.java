package com.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs.model.Review;
import com.cs.repository.ReviewService;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public String addReview(@RequestParam("reviewContent") String reviewContent) {
        // Lưu review vào cơ sở dữ liệu
        Review review = new Review();
        review.setReviewContent(reviewContent);
        reviewService.addReview(review);
        return "redirect:/reviews";
    }
        @GetMapping("")
        public String showReviews(Model model) {
            List<Review> reviews = reviewService.getAllReviews();
            model.addAttribute("reviews", reviews);
            return "reviewList";
        }    
}

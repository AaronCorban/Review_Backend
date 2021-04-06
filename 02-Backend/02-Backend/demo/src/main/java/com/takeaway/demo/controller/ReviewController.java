package com.takeaway.demo.controller;

import com.takeaway.demo.dao.ReviewRepository;
import com.takeaway.demo.entity.Review;
import com.takeaway.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    //get all reviews
    @GetMapping
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    //get review by id
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable (value = "id") long reviewID){
        return this.reviewRepository.findById(reviewID).orElseThrow(() -> new ResourceNotFoundException("Review not found: " + reviewID));
    }

    //create review
    @PostMapping
    public Review createReview(@RequestBody Review review){
        return this.reviewRepository.save(review);
    }

    //update review
    @PutMapping("/{id}")
    public Review updateReview(@RequestBody Review review, @PathVariable (value = "id") long reviewID){
        Review existingReview = this.reviewRepository.findById(reviewID).orElseThrow(() -> new ResourceNotFoundException("Review not found: " + reviewID));
        existingReview.setName(review.getName());
        existingReview.setRating(review.getRating());
        existingReview.setComment(review.getComment());
        return this.reviewRepository.save(existingReview);
    }

    //delete review by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable (value = "id") long reviewID){
        Review existingReview = this.reviewRepository.findById(reviewID).orElseThrow(() -> new ResourceNotFoundException("Review not found: " + reviewID));
        this.reviewRepository.delete(existingReview);
        return ResponseEntity.ok().build();
    }

}

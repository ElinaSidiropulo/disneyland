package org.example.service;

import org.example.entity.Attraction;
import org.example.entity.Review;
import org.example.entity.User;
import org.example.repository.AttractionRepository;
import org.example.repository.ReviewRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository,
                         AttractionRepository attractionRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.attractionRepository = attractionRepository;
    }

    public Review createReview(Review review) {
        // Явно загружаем полные объекты пользователя и аттракциона
        User user = userRepository.findById(review.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Attraction attraction = attractionRepository.findById(review.getAttraction().getId())
                .orElseThrow(() -> new IllegalArgumentException("Attraction not found"));

        review.setUser(user);
        review.setAttraction(attraction);

        return reviewRepository.save(review);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByAttractionId(Long attractionId) {
        return reviewRepository.findByAttractionId(attractionId);
    }

    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id).map(existingReview -> {
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());

            // при желании, можно также обновлять пользователя и аттракцион
            if (updatedReview.getUser() != null) {
                User user = userRepository.findById(updatedReview.getUser().getId())
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
                existingReview.setUser(user);
            }

            if (updatedReview.getAttraction() != null) {
                Attraction attraction = attractionRepository.findById(updatedReview.getAttraction().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Attraction not found"));
                existingReview.setAttraction(attraction);
            }

            return reviewRepository.save(existingReview);
        }).orElse(null);
    }

    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}

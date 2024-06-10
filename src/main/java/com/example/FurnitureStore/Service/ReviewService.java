package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Reviews;
import com.example.FurnitureStore.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service

public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    public Reviews findByUUID(UUID uuid) {
        return reviewRepository.findByUUID(uuid);
    }

    public List<Reviews> findReviewByUUIDcl(UUID uuid) {
        return reviewRepository.findReviewByUUIDcl(uuid);
    }
    public List<Reviews> findByUUIDGood(UUID uuid) {
        return reviewRepository.findByUUIDGood(uuid);
    }

    public Reviews findByUUID(UUID uuid, UUID uuidcl) {
        return reviewRepository.findByUUID(uuid,uuidcl);
    }

    public Reviews findByUUIDcl(UUID uuid, UUID uuidcl) {
        return reviewRepository.findByUUIDcl(uuid,uuidcl);
    }

    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }
    public Reviews updateReview(UUID id, Reviews updatedGood) {
        Reviews  good = reviewRepository.findByUUID(id);
        if (good != null) {
            good.setMark(updatedGood.getMark());
            good.setReview(updatedGood.getReview());
            return reviewRepository.save(good);
        }
        return null;
    }
    public Reviews createReview(Reviews client) {
        return reviewRepository.save(client);
    }
}

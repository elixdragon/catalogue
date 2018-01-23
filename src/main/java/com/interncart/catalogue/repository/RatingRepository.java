package com.interncart.catalogue.repository;

import com.interncart.catalogue.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {
    Rating findByUserIdAndProductId(String userId, String productId);
    List<Rating> findAllByProductId(String productId);
}

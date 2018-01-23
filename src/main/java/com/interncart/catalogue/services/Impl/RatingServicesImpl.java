package com.interncart.catalogue.services.Impl;

import com.interncart.catalogue.entity.Rating;
import com.interncart.catalogue.repository.RatingRepository;
import com.interncart.catalogue.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServicesImpl implements RatingServices {


    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public void rate(Rating newRating) {

        Rating ratingCheck = ratingRepository.findByUserIdAndProductId(newRating.getUserId(), newRating.getProductId());
        if (ratingCheck != null)
            newRating.setRatingId(ratingCheck.getRatingId());
        ratingRepository.save(newRating);
    }

    @Override
    public Double getRating(String productId) {
        List<Rating> ratingList = ratingRepository.findAllByProductId(productId);
        Double finalRating = ratingList.stream().mapToDouble(Rating::getStars).sum() / ratingList.size();
        return finalRating;
    }


}

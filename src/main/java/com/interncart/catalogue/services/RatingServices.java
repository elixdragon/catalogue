package com.interncart.catalogue.services;

import com.interncart.catalogue.entity.Rating;

public interface RatingServices {
    void rate(Rating rating);

    Double getRating(String productId);
}

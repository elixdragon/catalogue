package com.interncart.catalogue.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.StringJoiner;

@Document(collection = Rating.COLLECTION_NAME)
public class Rating {
    public static final String COLLECTION_NAME = "ratings";

    @Id
    private String ratingId;
    private String productId;
    private String userId;
    private Double stars;


    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public static String getCollectionName() {
        return COLLECTION_NAME;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        if (stars > 5)
            stars = 5d;
        this.stars = stars;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("COLLECTION_NAME = " + COLLECTION_NAME)
                .add("productId = " + productId)
                .add("ratingId = " + ratingId)
                .add("stars = " + stars)
                .add("userId = " + userId)
                .toString();
    }
}

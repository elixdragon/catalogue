package com.interncart.catalogue.dto;

public class RatingDTO {

    private String productId;
    private Double stars;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }
}

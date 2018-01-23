package com.interncart.catalogue.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.StringJoiner;

@Document(collection = Product.COLLECTION_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    public static final String COLLECTION_NAME = "products";

    @Id
    private String productId;
    private String productName;
    private Double productPrice;
    private String productBrand;
    private String productCategory;
    private String productImage;
    private Integer productUnit;
    private Date dateCreated;
    private Date dateModified;
    private String description;
    private HashMap<String, String> specifications;


    public Product(){
        this.dateModified = Calendar.getInstance().getTime();
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(Integer productUnit) {
        this.productUnit = productUnit;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
        if (dateModified == null)
            this.dateModified = Calendar.getInstance().getTime();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(HashMap<String, String> specifications) {
        this.specifications = specifications;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
                .add("COLLECTION_NAME = " + COLLECTION_NAME)
                .add("dateCreated = " + dateCreated)
                .add("dateModified = " + dateModified)
                .add("description = " + description)
                .add("productBrand = " + productBrand)
                .add("productCategory = " + productCategory)
                .add("productId = " + productId)
                .add("productImage = " + productImage)
                .add("productName = " + productName)
                .add("productPrice = " + productPrice)
                .add("productUnit = " + productUnit)
                .add("specifications = " + specifications)
                .toString();
    }
}

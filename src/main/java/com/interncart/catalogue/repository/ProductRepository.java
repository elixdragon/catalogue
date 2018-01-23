package com.interncart.catalogue.repository;

import com.interncart.catalogue.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByProductUnitGreaterThan(Integer productUnit);

//    @Query("{ 'dateModified' : { $gt: ?0 } }")
    List<Product> findAllByDateModifiedGreaterThan(Date dateModified);

    List<Product> findAllByProductIdIn(List<String> productIdList);
}

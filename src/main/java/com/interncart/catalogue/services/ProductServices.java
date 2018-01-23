package com.interncart.catalogue.services;

import com.interncart.catalogue.dto.ProductDTO;
import com.interncart.catalogue.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProductServices {

    void add(Product product);

    void remove(Product product);

    void remove(String productId);

    void updateQuantity(String productId, int delta);

    void updateBulk(Map<String, Integer> updateMap);

    List<ProductDTO> findAll();

    List<ProductDTO> findAll(@NotNull Date dateModified);

    List<ProductDTO> findByProductIds(List<String> productIdList);

    Product findByProductId(String productId);


    List<ProductDTO> findLatest(Integer size);

    void addBulk(List<Product> productList);
}

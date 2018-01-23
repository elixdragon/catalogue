package com.interncart.catalogue.services.Impl;

import com.interncart.catalogue.Values;
import com.interncart.catalogue.dto.ProductDTO;
import com.interncart.catalogue.entity.Product;
import com.interncart.catalogue.repository.ProductRepository;
import com.interncart.catalogue.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public static final Integer MAX_PAGE_SIZE = 30;

    @Override
    public void add(Product product) {
        if (product.getProductUnit() == null)
            product.setProductUnit(0);
        if (product.getProductImage() == null)
            product.setProductImage(Values.DEFAULT_PRODUCT_IMAGE);
        product.setDateCreated(Date.from(Instant.now()));
        productRepository.save(product);
    }

    @Override
    public void remove(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void remove(String productId) {
        productRepository.delete(productId);
    }

    @Override
    public void updateQuantity(String productId, int delta) {
        Product product = productRepository.findOne(productId);
        if (product == null)
            return;
        product.setProductUnit(product.getProductUnit() + delta);
        add(product);
    }

    @Override
    public void updateBulk(Map<String, Integer> updateMap) {
        updateMap.forEach(this::updateQuantity);
    }

    @Override
    public List<ProductDTO> findAll() {
        //only return products that are in stock
        List<Product> productList =  productRepository.findAllByProductUnitGreaterThan(0);
        return productList.stream().map(ProductDTO::new).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<ProductDTO> findAll(@NotNull Date dateModified) {
        List <Product> productList = productRepository.findAllByDateModifiedGreaterThan(dateModified);
//        List<Product> productList;
//        productList = mongoTemplate.find(new Query().addCriteria(Criteria.where("dateModified")))
        return productList.stream().map(ProductDTO::new).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<ProductDTO> findByProductIds(List<String> productIdList) {
        List<Product> allByProductId = productRepository.findAllByProductIdIn(productIdList);
        return allByProductId.stream().map(ProductDTO::new).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Product findByProductId(String productId) {
        return productRepository.findOne(productId);
    }

    @Override
    public List<ProductDTO> findLatest(Integer size) {
        if (size > MAX_PAGE_SIZE)
            size = MAX_PAGE_SIZE;
        List <ProductDTO> productDTOList = new ArrayList<> ();
        productRepository.findAll(new PageRequest(0, size, new Sort(Sort.Direction.DESC, "dateCreated"))).forEach(product -> productDTOList.add(new ProductDTO(product)));
        return productDTOList;
    }

    @Override
    public void addBulk(List<Product> productList) {
        productRepository.save(productList);
    }


}

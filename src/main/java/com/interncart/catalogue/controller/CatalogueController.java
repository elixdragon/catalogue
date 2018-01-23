package com.interncart.catalogue.controller;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.interncart.catalogue.MyUtils;
import com.interncart.catalogue.dto.ProductDTO;
import com.interncart.catalogue.entity.Product;
import com.interncart.catalogue.entity.Rating;
import com.interncart.catalogue.services.ProductServices;
import com.interncart.catalogue.services.RatingServices;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/catalogue")

public class CatalogueController {



    @Autowired
    ProductServices productServices;

    @Autowired
    RatingServices ratingServices;

    @Autowired
    ObjectNode objectNode;

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody Product product) {
        productServices.add(product);
        objectNode.removeAll();
        objectNode.put("message", "Added : " + product.getProductName());
        return new ResponseEntity<>(objectNode, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("productId") @NotNull String productId){
        return new ResponseEntity<> (productServices.findByProductId(productId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{productId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("productId") String productId) {
        productServices.remove(productId);
        objectNode.removeAll();
        objectNode.put("message", "Deleted " + productId);
        return new ResponseEntity<>(objectNode, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBulk(@RequestBody @NotNull HashMap<String, Integer> updateMap) {
        //increase | decrease quantity of each pid by delta
        productServices.updateBulk(updateMap);
        objectNode.removeAll();
        objectNode.put("Message", "Data inserted");
        return new ResponseEntity<>(objectNode, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(){
        List<ProductDTO> productList = productServices.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/all", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(@RequestBody @NotNull Product product){
        List<ProductDTO> productDTOList = productServices.findAll(product.getDateModified());
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.POST, value = "/list", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@RequestBody @NotNull List<String> productIdList){
        return new ResponseEntity<>(productServices.findByProductIds(productIdList), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/latest", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> latest(@RequestParam(value = "size", defaultValue = "20", required = false) Integer size){
        return new ResponseEntity<>(productServices.findLatest(size), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bulk", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBulk(@RequestBody @NotNull ArrayList<Product> productList){
        productServices.addBulk(productList);
        objectNode.removeAll();
        objectNode.put("message", "Products added");
        return new ResponseEntity<>(objectNode, HttpStatus.OK);
    }



   /**
    * RATING API ENDPOINTS
    */

    @RequestMapping(method = RequestMethod.POST, value = "/rating", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> setRating(@RequestBody Rating rating) {
        ratingServices.rate(rating);
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("message", "Rating recorded!");
        return new ResponseEntity<>(objectNode, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating/{pid}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<?> getRating(@PathVariable("pid") String productId){
        Double stars = ratingServices.getRating(productId);
        return new ResponseEntity<>(MyUtils.makeJSONString("rating", String.format("%.2f", stars)), HttpStatus.OK);
    }


}

package com.fastkart.ecomm.FastKart.Ecomm.controller;


import com.fastkart.ecomm.FastKart.Ecomm.dto.*;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductInformation;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductWithBid;
import com.fastkart.ecomm.FastKart.Ecomm.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@Component
@RequestMapping("/api/v1/seller")
@CrossOrigin
@Slf4j
public class SellerController {

    @Autowired
    private ProductService productService;
    @PostMapping("/product")
    public String addProduct(@RequestBody AddProductRequest request){
        log.info("Inside seller controller");
        log.info("Endpoint called: /product");
        log.info("Verb: POST");
        log.info("Request: {}", request.toString());
        return productService.addProduct(request);
    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductWithBid>> getProduct(@RequestParam(value = "id", required = true) Integer id) {
        log.info("Inside seller controller");
        log.info("Endpoint called: /product");
        log.info("Verb: GET");
        log.info("Query param id {}", id);
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductInformation>> getProducsBySeller(@RequestParam("sellerid") int id){
        log.info("Inside seller controller");
        log.info("Endpoint called: /products");
        log.info("Verb: GET");
        log.info("Query param sellerid {}", id);
        List<ProductInformation> productList = productService.getProductsBySeller(id);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}

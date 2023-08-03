package com.fastkart.ecomm.FastKart.Ecomm.controller;


import com.fastkart.ecomm.FastKart.Ecomm.dto.AddProductRequest;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import com.fastkart.ecomm.FastKart.Ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
@RequestMapping("/api/v1/seller")
public class SellerController {

    @Autowired
    private ProductService productService;
    @PostMapping("/product")
    public String addProduct(@RequestBody AddProductRequest request){
        return productService.addProduct(request);
    }
    @GetMapping("/product")
    public ResponseEntity<Product> getProduct(@RequestParam("id") int id){
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducsBySeller(@RequestParam("sellerid") int id){
        return new ResponseEntity<>(productService.getProductsBySeller(id), HttpStatus.OK);
    }
}

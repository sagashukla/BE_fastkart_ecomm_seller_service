package com.fastkart.ecomm.FastKart.Ecomm.controller;

import com.fastkart.ecomm.FastKart.Ecomm.dto.AddProductRequest;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import com.fastkart.ecomm.FastKart.Ecomm.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private SellerController sellerController;
    @Test
    void addProduct() {
        String success = "Product has been added!";

        AddProductRequest request = AddProductRequest
                .builder()
                .minBidAmount(1f)
                .sellerId(1)
                .name("test")
                .description("test")
                .category("test")
                .build();




        when(productService.addProduct(request)).thenReturn(success);

        assertEquals(success, sellerController.addProduct(request));

    }

    @Test
    void getProduct() {
        Integer id = 1;

        Product product = Product
                .builder()
                .name("test")
                .sellerId(1)
                .description("test")
                .category("test")
                .minBidAmount(1f)
                .id(1)
                .build();

        when(productService.getProduct(id)).thenReturn(product);

        assertEquals(id, sellerController.getProduct(id).getBody().getId());

    }

    @Test
    void getProducsBySeller() {
        Integer id = 1;

        Product product = Product
                .builder()
                .name("test")
                .sellerId(1)
                .description("test")
                .category("test")
                .minBidAmount(1f)
                .id(1)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productService.getProductsBySeller(id)).thenReturn(productList);
        assertEquals(1, sellerController.getProducsBySeller(id).getBody().size());
    }
}
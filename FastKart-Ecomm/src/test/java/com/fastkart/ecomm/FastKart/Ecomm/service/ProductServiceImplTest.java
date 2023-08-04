package com.fastkart.ecomm.FastKart.Ecomm.service;

import com.fastkart.ecomm.FastKart.Ecomm.dto.AddProductRequest;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import com.fastkart.ecomm.FastKart.Ecomm.exception.ProductException;
import com.fastkart.ecomm.FastKart.Ecomm.repository.ProductDetailsRepository;
import com.fastkart.ecomm.FastKart.Ecomm.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private ProductService productService;
    @Test
    void addProductSuccess_Test() {
        AddProductRequest request = AddProductRequest
                .builder()
                .name("iPhone 8")
                .description("iPhone 8")
                .minBidAmount(10f)
                .category("Mobile")
                .sellerId(2)
                .build();

        Product product = Product
                .builder()
                .id(2)
                .name("iPhone 8")
                .description("iPhone 8")
                .minBidAmount(10f)
                .category("Mobile")
                .sellerId(2)
                .build();

        when(productRepository.save(product)).thenReturn(product);
        assertNotNull(productService.addProduct(request));
    }

    @Test
    void getProductSuccess_Test() {
        Product product = Product
                .builder()
                .id(2)
                .name("iPhone 8")
                .description("iPhone 8")
                .minBidAmount(10f)
                .category("Mobile")
                .sellerId(2)
                .build();

        when(productRepository.findById(2)).thenReturn(Optional.ofNullable(product));
        assertNotNull(productService.getProduct(2));
    }

    @Test
    void getProductProductDoesnotExist_Test() {
        Product product = Product
                .builder()
                .id(2)
                .name("iPhone 8")
                .description("iPhone 8")
                .minBidAmount(10f)
                .category("Mobile")
                .sellerId(2)
                .build();

        when(productRepository.findById(2)).thenReturn(Optional.empty());
        Throwable exception = assertThrows(ProductException.class, () -> productService.getProduct(2));
        assertEquals("Product does not exist", exception.getMessage());
    }

    @Test
    void getProductsBySeller() {

        Product product = Product
                .builder()
                .id(2)
                .name("iPhone 8")
                .description("iPhone 8")
                .minBidAmount(10f)
                .category("Mobile")
                .sellerId(2)
                .build();


        Product product1 = Product
                .builder()
                .id(2)
                .name("iPhone 8")
                .description("iPhone 8")
                .minBidAmount(10f)
                .category("Mobile")
                .sellerId(2)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product);

        when(productDetailsRepository.getProductsBySeller(2)).thenReturn(productList);

        assertEquals(2, productService.getProductsBySeller(2).size());
    }
}
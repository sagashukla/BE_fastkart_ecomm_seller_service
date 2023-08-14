package com.fastkart.ecomm.FastKart.Ecomm.controller;

import com.fastkart.ecomm.FastKart.Ecomm.dto.AddProductRequest;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductInformation;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductInformationImpl;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductWithBid;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Category;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductWithBidImpl;
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

        Category category = Category.builder()
                .categoryName("category")
                .categoryId(2)
                .build();

        AddProductRequest request = AddProductRequest
                .builder()
                .minBidAmount(1f)
                .sellerId(1)
                .name("test")
                .description("test")
                .categoryId(2)
                .build();

        when(productService.addProduct(request)).thenReturn(success);

        assertEquals(success, sellerController.addProduct(request));

    }

    @Test
    void getProduct() {
        Integer id = 1;

        ProductWithBid productWithBid = ProductWithBidImpl.builder()
                .id(1)
                .name("Car")
                .bidAmount(10f)
                .sellerName("F Seller")
                .bidderName("S Buyer")
                .bidCreatedAt(System.currentTimeMillis())
                .categoryName("Automobile")
                .minimumBidAmount(1f)
                .build();

        ProductWithBid productWithBid1 = ProductWithBidImpl.builder()
                .id(1)
                .name("Car")
                .bidAmount(10f)
                .sellerName("F Seller")
                .bidderName("S Buyer")
                .bidCreatedAt(System.currentTimeMillis())
                .categoryName("Automobile")
                .minimumBidAmount(1f)
                .build();

        List<ProductWithBid> productWithBidList = new ArrayList<>();
        productWithBidList.add(productWithBid);
        productWithBidList.add(productWithBid1);

        when(productService.getProduct(id)).thenReturn(productWithBidList);
        assertEquals(2, sellerController.getProduct(id).getBody().size());

    }

    @Test
    void getProducsBySeller() {
        Integer id = 1;

        ProductInformation productInformation = ProductInformationImpl
                .builder()
                .description("Product")
                .categoryName("Category")
                .createdAt(System.currentTimeMillis())
                .maxBidAmount(10f)
                .name("Product")
                .build();

        ProductInformation productInformation1 = ProductInformationImpl
                .builder()
                .description("Product")
                .categoryName("Category")
                .createdAt(System.currentTimeMillis()).
                 maxBidAmount(10f)
                .name("Product")
                .build();

        List<ProductInformation> productInformationList = new ArrayList<>();
        productInformationList.add(productInformation);
        productInformationList.add(productInformation1);


        when(productService.getProductsBySeller(id)).thenReturn(productInformationList);
        assertEquals(2, sellerController.getProducsBySeller(id).getBody().size());
    }
}
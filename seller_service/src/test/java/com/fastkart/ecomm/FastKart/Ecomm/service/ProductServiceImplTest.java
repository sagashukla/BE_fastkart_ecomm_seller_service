package com.fastkart.ecomm.FastKart.Ecomm.service;

import com.fastkart.ecomm.FastKart.Ecomm.dto.AddProductRequest;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Category;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import com.fastkart.ecomm.FastKart.Ecomm.exception.ProductException;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductInformation;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductInformationImpl;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductWithBid;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductWithBidImpl;
import com.fastkart.ecomm.FastKart.Ecomm.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @Test
    void addProductSuccess_Test() {
        Category category = Category.builder()
                .categoryName("category")
                .categoryId(2)
                .build();
        AddProductRequest request = AddProductRequest
                .builder()
                .name("iPhone 8")
                .description("iPhone 8")
                .minBidAmount(10f)
                .categoryId(2)
                .sellerId(2)
                .build();

        Product product = Product
                .builder()
                .id(2)
                .name("iPhone 8")
                .description("iPhone 8")
                .minBidAmount(10f)
                .category(category)
                .sellerId(2)
                .build();

        when(productRepository.save(Mockito.any())).thenReturn(product);
        assertNotNull(productService.addProduct(request));
    }

    @Test
    void getProductSuccess_Test() {
        ProductWithBid productWithBid = ProductWithBidImpl
                .builder()
                .bidCreatedAt(System.currentTimeMillis())
                .minimumBidAmount(10f)
                .bidderName("sagar buyer")
                .sellerName("sagar seller")
                .bidAmount(1001f)
                .categoryName("Automobile")
                .name("Car")
                .id(1)
                .build();

        ProductWithBid productWithBid1 = ProductWithBidImpl
                .builder()
                .bidCreatedAt(System.currentTimeMillis())
                .minimumBidAmount(10f)
                .bidderName("sagar buyer")
                .sellerName("sagar seller")
                .bidAmount(1001f)
                .categoryName("Automobile")
                .name("Car")
                .id(1)
                .build();

        List<ProductWithBid> productWithBidList = new ArrayList<>();
        productWithBidList.add(productWithBid);
        productWithBidList.add(productWithBid1);
        when(productRepository.getProductWithBids(2)).thenReturn((productWithBidList));
        assertEquals(2, productService.getProduct(2).size());
    }

    @Test
    void getProductProductInvalidProductId_Fail() {
        Throwable exception = assertThrows(ProductException.class, () -> productService.getProduct(0));
        assertEquals("Wrong product id", exception.getMessage());
    }

    @Test
    void getProductsBySeller_Success() {

        ProductInformation productInformation = ProductInformationImpl
                .builder()
                .createdAt(System.currentTimeMillis())
                .bidAmount(100f)
                .categoryName("Automobile")
                .name("Tata Nexon")
                .bidAmount(1001f)
                .description("Tata nexon")
                .build();

        ProductInformation productInformation1 = ProductInformationImpl
                .builder()
                .createdAt(System.currentTimeMillis())
                .bidAmount(100f)
                .categoryName("Automobile")
                .name("Tata Nexon")
                .bidAmount(1001f)
                .description("Tata nexon")
                .build();

        List<ProductInformation> productInformationList = new ArrayList<>();
        productInformationList.add(productInformation);
        productInformationList.add(productInformation1);

        when(productRepository.getProductsBySeller(2)).thenReturn(productInformationList);

        assertEquals(2, productService.getProductsBySeller(2).size());
    }

    @Test
    void getProductsBySellerInvalidSellerId_Fail() {
        Throwable exception = assertThrows(ProductException.class, () -> productService.getProductsBySeller(0));
        assertEquals("Wrong seller id", exception.getMessage());
    }
}
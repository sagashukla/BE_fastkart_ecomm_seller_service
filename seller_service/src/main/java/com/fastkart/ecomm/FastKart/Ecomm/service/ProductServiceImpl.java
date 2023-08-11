package com.fastkart.ecomm.FastKart.Ecomm.service;

import com.fastkart.ecomm.FastKart.Ecomm.common.Utils;
import com.fastkart.ecomm.FastKart.Ecomm.dto.*;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Category;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import com.fastkart.ecomm.FastKart.Ecomm.enums.Categories;
import com.fastkart.ecomm.FastKart.Ecomm.exception.ProductException;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductInformation;
import com.fastkart.ecomm.FastKart.Ecomm.repository.ProductRepository;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductWithBid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addProduct(AddProductRequest request) {
        log.info("Inside ProductServiceImpl.");
        log.info("Running addProduct()");
        validateRequest(request);

        Category category = Category
                .builder()
                .categoryId(request.getCategoryId())
                .categoryName(getCategoryName(request.getCategoryId()))
                .build();

        Product product = Product
                .builder()
                        .name(request.getName())
                                .description(request.getDescription())
                                        .minBidAmount(request.getMinBidAmount())
                                                        .sellerId(request.getSellerId())
                .category(category)
                .createAt(System.currentTimeMillis())
                                                                .build();
        Product savedProduct = productRepository.save(product);
        log.info("Product with id {} created.", savedProduct.getId());
        return "Product has been added!";
    }

    private String getCategoryName(Integer categoryId) {
        if(categoryId == 1){
            return Categories.Automobile.toString();
        }
        else if(categoryId == 2){
            return Categories.Clothes.toString();
        }
        else{
            return Categories.Electronics.toString();
        }
    }

    @Override
    public List<ProductWithBid> getProduct(Integer id) {
        log.info("Inside ProductServiceImpl.");
        log.info("Running getProduct()");
        if(id <= 0 || id == null){
            throw new ProductException("Wrong product id");
        }
        List<ProductWithBid> productWithBidList = productRepository.getProductWithBids(id);
        log.info("Product with bid list {}", productWithBidList);
        return productWithBidList;
    }

    @Override
    public List<ProductInformation> getProductsBySeller(Integer id) {
        log.info("Inside ProductServiceImpl.");
        log.info("Running getProductsBySeller()");
        if(id <= 0 || id == null){
            throw new ProductException("Wrong seller id");
        }
        List<ProductInformation> productInformationList = productRepository.getProductsBySeller(id);
        log.info("Product information list {}", productInformationList);
        return productInformationList;
    }

    private void validateRequest(AddProductRequest request) {
        if(Utils.validateString(request.getName())){
            throw new ProductException("Product name cannot be null or empty");
        }
        else if(Utils.validateString(request.getDescription())){
            throw new ProductException("Description cannot be null or empty");
        }
        else if(Utils.valiateAmount(request.getMinBidAmount())){
            throw new ProductException("Bid amount cannot be null, 0.0 or negative");
        }
        else if(Utils.valiateId(request.getSellerId())){
            throw new ProductException("Seller cannot be null, 0 or negative");
        }
        else if(Utils.valiateId(request.getCategoryId())){
            throw new ProductException("Category id cannot be null, 0 or negative");
        }
    }
}

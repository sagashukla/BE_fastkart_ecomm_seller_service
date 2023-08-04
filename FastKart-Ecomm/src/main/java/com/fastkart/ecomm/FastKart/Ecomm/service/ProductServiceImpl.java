package com.fastkart.ecomm.FastKart.Ecomm.service;

import com.fastkart.ecomm.FastKart.Ecomm.common.Utils;
import com.fastkart.ecomm.FastKart.Ecomm.dto.AddProductRequest;
import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import com.fastkart.ecomm.FastKart.Ecomm.exception.ProductException;
import com.fastkart.ecomm.FastKart.Ecomm.repository.ProductDetailsRepository;
import com.fastkart.ecomm.FastKart.Ecomm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Override
    public String addProduct(AddProductRequest request) {
        validateRequest(request);

        Product product = Product
                .builder()
                        .name(request.getName())
                                .description(request.getDescription())
                                        .minBidAmount(request.getMinBidAmount())
                                                .category(request.getCategory())
                                                        .sellerId(request.getSellerId())
                                                                .build();

        productRepository.save(product);
        return "Product has been added!";
    }

    @Override
    public Product getProduct(Integer id) {
        Optional<Product> productDb = productRepository.findById(id);

        if(productDb.isEmpty()){
            throw new ProductException("Product does not exist");
        }
        else{
            return productDb.get();
        }
    }

    @Override
    public List<Product> getProductsBySeller(Integer id) {
        productDetailsRepository.getProductsBySeller(id);
        return productDetailsRepository.getProductsBySeller(id);
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
        else if(Utils.validateString(request.getCategory())){
            throw new ProductException("Category cannot be null or empty");
        }
        else if(Utils.valiateId(request.getSellerId())){
            throw new ProductException("Seller cannot be null, 0 or negative");
        }
    }
}

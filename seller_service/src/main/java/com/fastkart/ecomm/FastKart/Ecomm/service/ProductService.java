package com.fastkart.ecomm.FastKart.Ecomm.service;

import com.fastkart.ecomm.FastKart.Ecomm.dto.*;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductInformation;
import com.fastkart.ecomm.FastKart.Ecomm.projection.ProductWithBid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public String addProduct(AddProductRequest request);
    public List<ProductWithBid> getProduct(Integer id);
    public List<ProductInformation> getProductsBySeller(Integer id);
}

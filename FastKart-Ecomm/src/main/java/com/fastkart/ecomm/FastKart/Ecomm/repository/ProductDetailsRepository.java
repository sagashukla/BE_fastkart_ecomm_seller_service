package com.fastkart.ecomm.FastKart.Ecomm.repository;

import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDetailsRepository extends CrudRepository<Product, Integer> {
    @Query(
            value = "SELECT * FROM fastkart.product where seller_id = ?1",
            nativeQuery = true)
    public List<Product> getProductsBySeller(int sellerId);
}

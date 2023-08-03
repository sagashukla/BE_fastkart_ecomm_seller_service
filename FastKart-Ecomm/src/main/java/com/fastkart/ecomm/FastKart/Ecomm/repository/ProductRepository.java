package com.fastkart.ecomm.FastKart.Ecomm.repository;

import com.fastkart.ecomm.FastKart.Ecomm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(Integer id);
}

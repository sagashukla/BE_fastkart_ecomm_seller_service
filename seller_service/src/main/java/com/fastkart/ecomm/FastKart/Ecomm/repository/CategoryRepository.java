package com.fastkart.ecomm.FastKart.Ecomm.repository;

import com.fastkart.ecomm.FastKart.Ecomm.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

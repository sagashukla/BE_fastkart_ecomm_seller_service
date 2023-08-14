package com.fastkart.ecomm.FastKart.Ecomm.service;

import com.fastkart.ecomm.FastKart.Ecomm.entity.Category;
import com.fastkart.ecomm.FastKart.Ecomm.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategories() {
        log.info("Inside CategoryServiceImpl");
        List<Category> categoryList = categoryRepository.findAll();
        log.info("Categories fetched {}", categoryList);
        return categoryList;
    }
}

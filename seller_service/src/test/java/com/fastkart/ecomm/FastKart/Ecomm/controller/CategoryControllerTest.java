package com.fastkart.ecomm.FastKart.Ecomm.controller;

import com.fastkart.ecomm.FastKart.Ecomm.entity.Category;
import com.fastkart.ecomm.FastKart.Ecomm.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private CategoryController categoryController;
    @Test
    void getCategories() {
        Category category = Category.builder()
                .categoryName("Automobile")
                .categoryId(1)
                .build();

        Category category2 = Category.builder()
                .categoryName("Electronics")
                .categoryId(2)
                .build();

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category2);
        categoryList.add(category);
        when(categoryService.getCategories()).thenReturn(categoryList);
        assertEquals(2, categoryController.getCategories().getBody().size());

    }
}
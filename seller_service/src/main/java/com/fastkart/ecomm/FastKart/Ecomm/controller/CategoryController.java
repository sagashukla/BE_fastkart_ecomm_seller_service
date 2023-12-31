package com.fastkart.ecomm.FastKart.Ecomm.controller;


import com.fastkart.ecomm.FastKart.Ecomm.entity.Category;
import com.fastkart.ecomm.FastKart.Ecomm.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Component
@RequestMapping("/api/v1/category")
@CrossOrigin
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        log.info("Inside category controller");
        log.info("Endpoint called: /api/v1/category");
        log.info("Verb: GET");
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }
}

package com.example.be.controller;

import com.example.be.payload.request.CategoryRequest;
import com.example.be.repository.CategoryRepository;
import com.example.be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/category")
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @Autowired
  CategoryRepository categoryRepository;

  @PostMapping("/add")
  public ResponseEntity<Object> addCategory(
          @RequestBody CategoryRequest categoryRequest) {
    return categoryService.addCtg(categoryRequest);
  }
}

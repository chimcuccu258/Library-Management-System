package com.example.be.service;

import com.example.be.model.Category;
import com.example.be.payload.request.CategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

  ResponseEntity<Object> addCtg(CategoryRequest categoryRequest);
}

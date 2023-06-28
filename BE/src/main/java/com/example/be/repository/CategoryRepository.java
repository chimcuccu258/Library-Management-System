package com.example.be.repository;

import com.example.be.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
  boolean existsByCtgName(String ctgName);
  boolean existsById(Long id);
}

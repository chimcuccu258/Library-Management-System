package com.example.be.service.impl;

import com.example.be.model.Category;
import com.example.be.payload.request.CategoryRequest;
import com.example.be.payload.response.CategoryResponse;
import com.example.be.payload.response.ListDataResponse;
import com.example.be.repository.CategoryRepository;
import com.example.be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  CategoryRepository categoryRepository;


  @Override
  public ResponseEntity<Object> addCtg(CategoryRequest categoryRequest) {
    try {
      String ctgName = categoryRequest.getCtgName();

      if (categoryRepository.existsByCtgName(ctgName)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Category already exists!");
      }
      if (!validateCtg(ctgName)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid category name!");
      }

      Category category = new Category();
      category.setCtgName(ctgName);
      categoryRepository.save(category);

      CategoryResponse categoryResponse = new CategoryResponse();
      categoryResponse.setCtgName(category.getCtgName());

      ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(categoryResponse).build();
      return ResponseEntity.status(HttpStatus.CREATED).body(listDataResponse);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from server with exception = " + e);
    }
  }

  public boolean validateCtg(String ctgName) {
    String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỂẾưạảấầẩẫậắằẳẵặẹẻẽềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+$";
    if (ctgName.matches(regex)) {
      System.out.println("Category name is valid!");
      return true;
    } else {
      System.out.println("Category name is invalid!");
      return false;
    }
  }


}

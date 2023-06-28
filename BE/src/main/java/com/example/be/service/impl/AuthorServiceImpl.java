package com.example.be.service.impl;

import com.example.be.model.Author;
import com.example.be.payload.request.AuthorRequest;
import com.example.be.payload.response.CategoryResponse;
import com.example.be.payload.response.ListDataResponse;
import com.example.be.repository.AuthorRepository;
import com.example.be.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
  @Autowired
  AuthorRepository authorRepository;

  @Override
  public ResponseEntity<Object> getAll() {
    try {
      List<Author> authors = authorRepository.findAll();
      ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(authors).build();
      return ResponseEntity.ok(listDataResponse);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> addAuth(AuthorRequest authorRequest) {
    try {
      String authorName = authorRequest.getAuthorName();
      if (authorRepository.existsByAuthorName(authorName)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author already exists!");
      }
      if (!validateAuthorN(authorName)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid category name!");
      }

      Author author = new Author();
      author.setAuthorName(authorName);
      authorRepository.save(author);

      CategoryResponse categoryResponse = new CategoryResponse();
      categoryResponse.setCtgName(author.getAuthorName());

      ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(categoryResponse).build();
      return ResponseEntity.status(HttpStatus.CREATED).body(listDataResponse);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> deleteAuth(Long id) {
    return null;
  }

  public boolean validateAuthorN(String authorName) {
    String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỂẾưạảấầẩẫậắằẳẵặẹẻẽềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+$";
    if (authorName.matches(regex)) {
      System.out.println("Author name is valid!");
      return true;
    } else {
      System.out.println("Author name is invalid!");
      return false;
    }
  }
}

package com.example.be.service.impl;

import com.example.be.model.Book;
import com.example.be.payload.request.BookRequest;
import com.example.be.payload.response.ListDataResponse;
import com.example.be.repository.BookRepository;
import com.example.be.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
  BookRepository bookRepository;

  @Override
  public ResponseEntity<Object> getAll() {
    try {
      List<Book> books = bookRepository.findAll();
      ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(books).build();
      return ResponseEntity.ok(listDataResponse);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> addBook(BookRequest bookRequest) {
    try {

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }
}

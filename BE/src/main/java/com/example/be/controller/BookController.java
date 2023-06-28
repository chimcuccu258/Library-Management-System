package com.example.be.controller;

import com.example.be.payload.request.BookRequest;
import com.example.be.repository.BookRepository;
import com.example.be.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/book")
public class BookController {

  @Autowired
  BookService bookService;

  @Autowired
  BookRepository bookRepository;

  @GetMapping("/get-all")
  public ResponseEntity<Object> getAllBooks() {
    return bookService.getAll();
  }

  @PostMapping("/add")
  public ResponseEntity<Object> addBook(
          @RequestBody BookRequest bookRequest) {
    return bookService.addBook(bookRequest);
  }
}

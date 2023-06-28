package com.example.be.service;

import com.example.be.payload.request.BookRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookService {

  ResponseEntity<Object> getAll();

  ResponseEntity<Object> addBook(BookRequest bookRequest);
}

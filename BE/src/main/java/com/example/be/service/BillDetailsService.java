package com.example.be.service;

import com.example.be.payload.request.BillDetailsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BillDetailsService {
  ResponseEntity<Object> addBdt(BillDetailsRequest billDetailsRequest);

  ResponseEntity<Object> getAll();
}

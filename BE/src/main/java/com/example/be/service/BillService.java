package com.example.be.service;

import com.example.be.payload.request.BillRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
  ResponseEntity<Object> getAll();

  ResponseEntity<Object> createBill(BillRequest billRequest);

  ResponseEntity<Object> returnBook(Long billId);

  ResponseEntity<Object> getBillById(Long billId);
}

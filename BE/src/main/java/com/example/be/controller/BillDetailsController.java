package com.example.be.controller;

import com.example.be.payload.request.BillDetailsRequest;
import com.example.be.repository.BillDetailsRepository;
import com.example.be.service.BillDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/billDetails")
public class BillDetailsController {
  @Autowired
  BillDetailsService billDetailsService;

  @Autowired
  BillDetailsRepository billDetailsRepository;

  @GetMapping("/get-all")
  public ResponseEntity<Object> getAllBillDetails() {
    return billDetailsService.getAll();
  }

  @PostMapping("/add")
  public ResponseEntity<Object> addBillDetails(
          @RequestBody BillDetailsRequest billDetailsRequest) {
    return billDetailsService.addBdt(billDetailsRequest);
  }
}

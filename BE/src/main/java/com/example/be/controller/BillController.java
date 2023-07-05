package com.example.be.controller;

import com.example.be.payload.request.BillRequest;
import com.example.be.repository.BillRepository;
import com.example.be.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/bill")
public class BillController {
  @Autowired
  BillService billService;

  @Autowired
  BillRepository billRepository;

  @GetMapping("/get-all")
  public ResponseEntity<Object> getAllBills() {
    return billService.getAll();
  }

  @GetMapping("/getBill/{billId}")
  public ResponseEntity<Object> getBillById(@PathVariable Long billId) {
    return billService.getBillById(billId);
  }

  @PostMapping("/create")
  public ResponseEntity<Object> createBill(
          @RequestBody BillRequest billRequest) {
    return billService.createBill(billRequest);
  }

  @GetMapping("/return/{billId}")
  public ResponseEntity<Object> returnBook(@PathVariable Long billId) {
    return billService.returnBook(billId);
  }
}

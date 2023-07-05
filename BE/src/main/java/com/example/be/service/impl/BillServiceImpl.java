package com.example.be.service.impl;

import com.example.be.model.Bill;
import com.example.be.model.BillDetails;
import com.example.be.model.Book;
import com.example.be.model.User;
import com.example.be.payload.dto.BillDetailsDto;
import com.example.be.payload.mapper.BillDetailsMapper;
import com.example.be.payload.request.BillRequest;
import com.example.be.payload.response.BillResponse;
import com.example.be.payload.response.ListDataResponse;
import com.example.be.repository.BillDetailsRepository;
import com.example.be.repository.BillRepository;
import com.example.be.repository.UserRepository;
import com.example.be.service.BillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {

  @Autowired
  BillRepository billRepository;

  @Autowired
  BillDetailsRepository billDetailsRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public ResponseEntity<Object> getAll() {
    try {
      List<Bill> bills = billRepository.findAll();
      List<BillResponse> billResponses = bills.stream().map(bill -> {
        BillResponse billResponse = new BillResponse();

        BeanUtils.copyProperties(bill, billResponse);
        billResponse.setUserId(bill.getUser().getId());
        List<BillDetailsDto> billDetailsDtoList = bill.getBillDetails().stream().map(
                billDetails -> BillDetailsMapper.getInstance().toDTO(billDetails)).toList();
        billResponse.setBillDetailsDtoList(billDetailsDtoList);
        return billResponse;
      }).toList();

      ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(billResponses).build();
      return ResponseEntity.ok(listDataResponse);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> createBill(BillRequest billRequest) {
    try {
      User user = userRepository.findById(billRequest.getUserId()).get();

      List<BillDetails> billDetailsList = billRequest.getBill_details_id().stream()
              .map(billDetailId -> billDetailsRepository.findById(billDetailId).get()).toList();

      if (billDetailsList.isEmpty()) {
        return ResponseEntity.badRequest().body("Empty bill details list.");
      }

      Long totalPrice = 0L;
      for (BillDetails billDetails : billDetailsList) {
        Book book = billDetails.getBook();
        int quantity = billDetails.getQuantity();

        if (book.getInventory() >= quantity) {
          book.setInventory(book.getInventory() - quantity);
        } else {
          return ResponseEntity.badRequest().body("Insufficient inventory for book: " + book.getId());
        }
        totalPrice += billDetails.getBook().getPrice() * billDetails.getQuantity();
      }
      Bill bill = new Bill();
      LocalDateTime issue_date = LocalDateTime.now();
      bill.setUser(user);
      bill.setIssue_date(issue_date);
      bill.setExpired_date(issue_date.plusDays(20));
      bill.setTotal_price(totalPrice);
      bill.setBillDetails(billDetailsList);

      billDetailsList.forEach(billDetails -> billDetails.setBill(bill));
      billRepository.save(bill);
      billDetailsRepository.saveAll(billDetailsList);

      List<BillDetailsDto> billDetailsDto =
              billDetailsList.stream().map(billDetails -> BillDetailsMapper.getInstance().toDTO(billDetails)
              ).toList();

      BillResponse billResponse = BillResponse.builder()
              .id(bill.getId())
              .userId(bill.getUser().getId())
              .issue_date(bill.getIssue_date())
              .expired_date(bill.getExpired_date())
              .total_price(bill.getTotal_price())
              .billDetailsDtoList(billDetailsDto)
              .build();
      return ResponseEntity.ok(billResponse);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> returnBook(Long billId) {
    try {
      Optional<Bill> optionalBill = billRepository.findById(billId);
      if (optionalBill.isPresent()) {
        Bill bill = optionalBill.get();
        List<BillDetails> billDetailsList = bill.getBillDetails();
        for (BillDetails billDetails : billDetailsList) {
          Book book = billDetails.getBook();
          int quantity = billDetails.getQuantity();
          book.setInventory(book.getInventory() + quantity);
        }
        bill.setIsReturned(true);
        billRepository.save(bill);
        ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("Books returned successfully.").data(bill).build();
        return ResponseEntity.ok(listDataResponse);
      } else {
        ListDataResponse<Object> listDataResponse =
                ListDataResponse.builder().message("Bill not found with ID: " + billId).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listDataResponse);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> getBillById(Long billId) {
    try {
      Optional<Bill> optionalBill = billRepository.findById(billId);
      if (optionalBill.isPresent()) {
        Bill bill = optionalBill.get();
        BillResponse billResponse = new BillResponse();

        BeanUtils.copyProperties(bill, billResponse);
        billResponse.setUserId(bill.getUser().getId());
        List<BillDetailsDto> billDetailsDtoList = bill.getBillDetails().stream()
                .map(billDetails -> BillDetailsMapper.getInstance().toDTO(billDetails))
                .toList();
        billResponse.setBillDetailsDtoList(billDetailsDtoList);
        return ResponseEntity.ok(billResponse);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Bill not found with ID: " + billId);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }


}

package com.example.be.service.impl;

import com.example.be.model.BillDetails;
import com.example.be.model.Book;
import com.example.be.payload.mapper.BillDetailsMapper;
import com.example.be.payload.request.BillDetailsRequest;
import com.example.be.payload.response.BillDetailsResponse;
import com.example.be.payload.response.ListDataResponse;
import com.example.be.repository.BillDetailsRepository;
import com.example.be.repository.BookRepository;
import com.example.be.service.BillDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailsServiceImpl implements BillDetailsService {

  @Autowired
  BillDetailsRepository billDetailsRepository;

  @Autowired
  BookRepository bookRepository;

  @Override
  public ResponseEntity<Object> addBdt(BillDetailsRequest billDetailsRequest) {
    try {
      if (!bookRepository.existsById(billDetailsRequest.getBookId())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not exists!");
      }
      Book book = bookRepository.findById(billDetailsRequest.getBookId()).get();
      if ((billDetailsRequest.getQuantity() < 0) || (billDetailsRequest.getQuantity() > book.getInventory())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity!");
      }

      BillDetails billDetails = new BillDetails();
      billDetails.setBook(book);
      billDetails.setQuantity(billDetailsRequest.getQuantity());
      billDetailsRepository.save(billDetails);

      return ResponseEntity.status(HttpStatus.CREATED)
              .body(BillDetailsMapper.getInstance().toResponse(billDetails));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> getAll() {
    try {
      List<BillDetails> billDetailsList = billDetailsRepository.findAll();
      List<BillDetailsResponse> billDetailsResponses = billDetailsList.stream().map(billDetails -> {

        BillDetailsResponse billDetailsResponse = new BillDetailsResponse();
        billDetailsResponse.setBookId(billDetails.getBook().getId());
        BeanUtils.copyProperties(billDetails, billDetailsResponse);

        return billDetailsResponse;
      }).toList();

      ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(billDetailsResponses).build();
      return ResponseEntity.ok(listDataResponse);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }


}

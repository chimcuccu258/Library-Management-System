package com.example.be.payload.request;

import lombok.Data;

@Data
public class BillDetailsRequest {
  private Long bookId;
  private Integer quantity;
}

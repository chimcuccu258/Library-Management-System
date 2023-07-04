package com.example.be.payload.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BillRequest {
  private Long userId;
  private List<Long> bill_details_id;
}

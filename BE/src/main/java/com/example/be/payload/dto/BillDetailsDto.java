package com.example.be.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailsDto {
  private Long id;
  private Long bookId;
  private String bookName;
  private Integer quantity;
}

package com.example.be.payload.response;

import com.example.be.payload.dto.BillDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {
  private Long id;
  private Long userId;
  private LocalDateTime issued_date;
  private LocalDateTime expired_date;
  private Long total_price;
//  private Long late_payment_fee;
  private List<BillDetailsDto> billDetailsDtoList;
}

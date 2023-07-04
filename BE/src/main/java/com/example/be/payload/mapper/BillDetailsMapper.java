package com.example.be.payload.mapper;

import com.example.be.model.BillDetails;
import com.example.be.payload.dto.BillDetailsDto;
import com.example.be.payload.response.BillDetailsResponse;
import org.springframework.beans.BeanUtils;

public class BillDetailsMapper {
  private static BillDetailsMapper INSTANCE;
  public static BillDetailsMapper getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new BillDetailsMapper();
    }
    return INSTANCE;
  }

  public BillDetails toEntity(BillDetailsDto dto) {
    BillDetails billDetails = new BillDetails();
    BeanUtils.copyProperties(dto, billDetails);
    return billDetails;
  }
  public BillDetailsDto toDTO(BillDetails billDetails) {
    BillDetailsDto dto = new BillDetailsDto();
    dto.setId(billDetails.getId());
    dto.setBookId(billDetails.getBook().getId());
    dto.setBookName(billDetails.getBook().getBookName());
    dto.setQuantity(billDetails.getQuantity());
    return dto;
  }

  public BillDetailsResponse toResponse(BillDetails billDetails) {
    return BillDetailsResponse.builder()
            .bookId(billDetails.getBook().getId())
            .quantity(billDetails.getQuantity())
            .build();
  }

}

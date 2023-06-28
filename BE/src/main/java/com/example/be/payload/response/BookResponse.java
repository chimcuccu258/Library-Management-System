package com.example.be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
  private String bookName;
  private String authorName;
  private Integer inventory;
  private Boolean isAvailable;
  private Float price;
  private String description;
  private Long authorId;
  private Long ctgId;
}

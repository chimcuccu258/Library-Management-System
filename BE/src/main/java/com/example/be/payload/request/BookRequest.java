package com.example.be.payload.request;

import lombok.Data;

@Data
public class BookRequest {
  private String bookName;
  private Integer inventory;
  private Double price;
  private String description;
  private Long authorId;
  private Long ctgId;
}

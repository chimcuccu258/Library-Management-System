package com.example.be.payload.request;

import lombok.Data;

@Data
public class BookRequest {
  private String bookName;
  private String authorName;
  private Integer inventory;
  private Boolean isAvailable;
  private Float price;
  private String description;
  private Long authorId;
  private Long ctgId;
}

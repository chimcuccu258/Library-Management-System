package com.example.be.payload.request;

import lombok.Data;

@Data
public class FindUserRequest {
  private String userName;
  private String phoneNumber;
  private String email;

}

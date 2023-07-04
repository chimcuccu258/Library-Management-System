package com.example.be.payload.response;

import lombok.Data;

@Data
public class LoginResponse {
  private String userName;
  private Boolean gender;
  private String phoneNumber;
  private String email;
}

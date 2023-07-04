package com.example.be.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
  private String userName;
  private Boolean gender;
  private String phoneNumber;
  private String email;
}

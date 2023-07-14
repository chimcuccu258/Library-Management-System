package com.example.be.payload.response;

import com.example.be.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
  private Long id;
  private String userName;
  private Boolean gender;
  private String phoneNumber;
  private String email;
  private List<String> roleNames;
}

package com.example.be.payload.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {
  @NotBlank
  private String userName;
  @NotBlank
  private String password;
  @NotBlank
  private Boolean gender;
  @NotBlank
  @Pattern(regexp = "^0\\d{9}$", message = "Phone number must have 10 digits")
  private String phoneNumber;
  @NotBlank
  @Email
  private String email;
}

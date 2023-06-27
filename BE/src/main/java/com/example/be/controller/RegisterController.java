package com.example.be.controller;

import com.example.be.payload.request.EditRequest;
import com.example.be.payload.request.FindUserRequest;
import com.example.be.payload.request.LoginRequest;
import com.example.be.payload.request.RegisterRequest;
import com.example.be.repository.UserRepository;
import com.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backend/user")
public class RegisterController {
  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/get-all")
  public ResponseEntity<Object> getAllUsers() {
    return userService.getAll();
  }

  @GetMapping("/get-user")
  public ResponseEntity<Object> getUser(
          @RequestBody FindUserRequest findUserRequest) {
    return userService.getUser(findUserRequest);
  }

  @PostMapping("/register")
  public ResponseEntity<Object> registerUser(
          @RequestBody RegisterRequest registerRequest) {
    return userService.createUser(registerRequest);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> validateUser(
          @RequestBody LoginRequest loginRequest) {
    return userService.validateUser(loginRequest);
  }

  @DeleteMapping("/delete/{userid}")
  public ResponseEntity<String> deleteUser(@PathVariable("userid") long id) {
    return userService.deleteUserById(id);
  }

  @PutMapping("/edit/{userId}")
  public ResponseEntity<Object> editUser(
          @PathVariable("userId") Long userId,
          @RequestBody EditRequest editRequest) {
    return userService.editUser(userId, editRequest);
  }
}

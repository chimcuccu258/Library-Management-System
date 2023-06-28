package com.example.be.service;

import com.example.be.model.User;
import com.example.be.payload.request.EditRequest;
import com.example.be.payload.request.FindUserRequest;
import com.example.be.payload.request.LoginRequest;
import com.example.be.payload.request.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
  List<User> getUsers();

  ResponseEntity<Object> createUser(RegisterRequest registerRequest);

  ResponseEntity<Object> validateUser(LoginRequest loginRequest);

  ResponseEntity<Object> getAll();

  ResponseEntity<Object> getUser(FindUserRequest findUserRequest);

  ResponseEntity<Object> editUser(Long id, EditRequest editRequest);

  ResponseEntity<Object> deleteUserById(Long id);
}

package com.example.be.service.impl;

import com.example.be.model.Role;
import com.example.be.model.User;
import com.example.be.model.UserRole;
import com.example.be.payload.request.EditRequest;
import com.example.be.payload.request.FindUserRequest;
import com.example.be.payload.request.LoginRequest;
import com.example.be.payload.request.RegisterRequest;
import com.example.be.payload.response.*;
import com.example.be.repository.RoleRepository;
import com.example.be.repository.UserRepository;
import com.example.be.repository.UserRoleRepository;
import com.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserRoleRepository userRoleRepository;

  @Override
  public List<User> getUsers() {
    return null;
  }

  @Override
  public ResponseEntity<Object> getAll() {
    try {
      List<User> userList = userRepository.findAll();
      List<UserResponse> userResponses = userList.stream().map(user -> {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(user.getUserName());
        userResponse.setGender(user.getGender());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setEmail(user.getEmail());
        List<UserRole> userRoles = user.getUserRole();
        List<String> roleNames = userRoles.stream().map(userRole -> userRole.getRole().getRoleName()).toList();
        userResponse.setRoleNames(roleNames);

        return userResponse;
      }).toList();
      ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(userResponses).build();
      return ResponseEntity.ok().body(listDataResponse);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> getUser(FindUserRequest findUserRequest) {
    try {
      User user = userRepository.findByUserNameOrPhoneNumberOrEmail(
              findUserRequest.getUserName(),
              findUserRequest.getPhoneNumber(),
              findUserRequest.getEmail());
      if (user != null) {
        UserResponse ListDataResponse = new UserResponse(
                user.getUserName(),
                user.getGender(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getUserRole().stream().map(userRole -> userRole.getRole().getRoleName()).collect(Collectors.toList())
        );
        return new ResponseEntity<>(ListDataResponse, HttpStatus.OK);
      } else {
        ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("User not found!").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listDataResponse);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> editUser(Long id, EditRequest editRequest) {
    try {
      Optional<User> userOptional = userRepository.findById(id);
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (editRequest.getPassword().isBlank()) {
          return ResponseEntity.badRequest().body("Password is required!");
        }
        user.setPassword(editRequest.getPassword());
        userRepository.save(user);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserName(user.getUserName());
        registerResponse.setGender(user.getGender());
        registerResponse.setPhoneNumber(user.getPhoneNumber());
        registerResponse.setEmail(user.getEmail());
        ListDataResponse<Object> listDataResponse = ListDataResponse.builder()
                .message("Password updated successfully for user " + registerResponse.getUserName()).data(registerResponse).build();
        return ResponseEntity.status(HttpStatus.OK).body(listDataResponse);
      } else {
        ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("User not found!").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listDataResponse);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> deleteUserById(Long id) {
    try {
      if (userRepository.existsById(id)) {
        userRepository.deleteById(id);
        ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("User " + id + " deleted successfully").build();
        return ResponseEntity.ok(listDataResponse);
      } else {
        ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("User not found").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listDataResponse);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> createUser(RegisterRequest registerRequest) {
    try {
      if (userRepository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Phone number already exists!");
      }
      if (userRepository.existsByEmail(registerRequest.getEmail())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Email already exists!");
      }
      if (!validateUserName(registerRequest.getUserName())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid username!");
      }
      if (!validatePhoneNumber(registerRequest.getPhoneNumber())) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid phone number!");
      }

      Optional<Role> role = roleRepository.findById(3L);
      if (role.isEmpty()) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Role not found!");
      }

      User user = new User();
      user.setUserName(registerRequest.getUserName());
      user.setPassword(registerRequest.getPassword());
      user.setGender(registerRequest.getGender());
      user.setPhoneNumber(registerRequest.getPhoneNumber());
      user.setEmail(registerRequest.getEmail());
      User savedUser = userRepository.save(user);

      UserRole userRole = UserRole.builder().role(role.get()).user(savedUser).build();
      userRoleRepository.save(userRole);

      RegisterResponse registerResponse = new RegisterResponse();
      registerResponse.setUserName(user.getUserName());
      registerResponse.setGender(user.getGender());
      registerResponse.setPhoneNumber(user.getPhoneNumber());
      registerResponse.setEmail(user.getEmail());
      ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(registerResponse).build();
      return ResponseEntity.status(HttpStatus.CREATED).body(listDataResponse);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from server with exception = " + e);
    }
  }

  @Override
  public ResponseEntity<Object> validateUser(LoginRequest loginRequest) {
    try {
      String email = loginRequest.getEmail();
      String password = loginRequest.getPassword();

      User foundUser = userRepository.findByEmail(email);
      if (foundUser != null) {
        if (foundUser.getPassword().equals(password)) {
          LoginResponse loginResponse = new LoginResponse();
          loginResponse.setId(foundUser.getId());
          loginResponse.setUserName(foundUser.getUserName());
          loginResponse.setGender(foundUser.getGender());
          loginResponse.setPhoneNumber(foundUser.getPhoneNumber());
          loginResponse.setEmail(foundUser.getEmail());

          List<String> roleNames = foundUser.getUserRole().stream()
                  .map(userRole -> userRole.getRole().getRoleName())
                  .collect(Collectors.toList());
          loginResponse.setRoleNames(roleNames);

          ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("OK").data(loginResponse).build();
          return ResponseEntity.status(HttpStatus.OK).body(listDataResponse);
        } else {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password!");
        }
      } else {
        ListDataResponse<Object> listDataResponse = ListDataResponse.builder().message("Email not found!").build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listDataResponse);
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("An exception occurred from the server with exception = " + e);
    }
  }

  public boolean validateUserName(String userName) {
    String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỂẾưạảấầẩẫậắằẳẵặẹẻẽềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+$";

    if (userName.matches(regex)) {
      System.out.println(("Username is valid"));
      return true;
    } else {
      System.out.println("Username is invalid");
      return false;
    }
  }

  public boolean validatePhoneNumber(String phoneNumber) {
    String regex = "^0\\d{9}$";

    if (phoneNumber.matches(regex)) {
      System.out.println("Phone number is valid");
      return true;
    } else {
      System.out.println("Phone number is invalid");
      return false;
    }
  }

  public boolean validateEmail(String email) {
    String regex = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})*$";

    if (email.matches(regex)) {
      System.out.println("Email is valid");
      return true;
    } else {
      System.out.println("Email is invalid");
      return false;
    }
  }
}

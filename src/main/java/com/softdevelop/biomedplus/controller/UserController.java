package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.service.UserService;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping()
  public ResponseEntity<List<UserDto>> getUsers() {
    List<UserDto> users = userService.getUsers();
    return ResponseEntity.ok(users);
  }

  @PostMapping()
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userRq) {
    LoggerEvent.info()
        .forClass(UserController.class)
        .withField("Action: ", "createUser")
        .withField("UserRequest - ", userRq)
        .log();
    UserDto userSaved = userService.createUser(userRq);
    return ResponseEntity.ok(userSaved);
  }

  @GetMapping("/email/{email}/{pass}")
  public ResponseEntity<UserDto> getUserByEmail(
          @PathVariable("email") String email,
          @PathVariable("pass") String pass) {
    UserDto user = userService.getUserByEmail(email, pass);
    return ResponseEntity.ok(user);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(
      @PathVariable("id") Long id) {
    LoggerEvent.info()
        .forClass(UserController.class)
        .withField("Action: ", "getUserById")
        .withField("Id", id)
        .log();
    UserDto response = userService.getuserById(id);
    return ResponseEntity.ok(response);
  }
}

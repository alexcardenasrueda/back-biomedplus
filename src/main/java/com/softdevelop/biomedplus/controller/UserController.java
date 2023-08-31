package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.service.TicketService;
import com.softdevelop.biomedplus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping()
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userRq) {
    UserDto userSaved = userService.createUser(userRq);
    return ResponseEntity.ok(userSaved);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserDto> getUserByEmail(
          @PathVariable("email") String email) {
    UserDto user = userService.getUserByEmail(email);
    return ResponseEntity.ok(user);
  }



}

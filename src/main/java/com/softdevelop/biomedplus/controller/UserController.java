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
  public ResponseEntity<Long> createUser(@Valid @RequestBody UserDto userRq) {
    Long userSaved = userService.createUser(userRq);
    return ResponseEntity.created(URI.create(String.format("users/%s", userSaved))).build();
  }


}

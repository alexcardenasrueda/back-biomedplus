package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.RolDto;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.service.RolService;
import com.softdevelop.biomedplus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

  private final RolService rolService;

  @GetMapping("")
  public ResponseEntity<List<RolDto>> getRoles() {
    List<RolDto> roles = rolService.getRoles();
    return ResponseEntity.ok(roles);
  }



}

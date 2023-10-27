package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.RolDto;
import com.softdevelop.biomedplus.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

  private final RolService rolService;

  @GetMapping()
  public ResponseEntity<List<RolDto>> getRoles() {
    List<RolDto> roles = rolService.getRoles();
    return ResponseEntity.ok(roles);
  }
}

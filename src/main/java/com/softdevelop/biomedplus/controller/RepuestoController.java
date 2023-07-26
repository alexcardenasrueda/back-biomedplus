package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.RepuestoDto;
import com.softdevelop.biomedplus.service.RepuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/repuestos")
@RequiredArgsConstructor
public class RepuestoController {

  private final RepuestoService repuestoService;

  @PostMapping()
  public ResponseEntity<Long> createReplacement(@Valid @RequestBody RepuestoDto repuestoRq) {
    Long repuestoSaved = repuestoService.createReplacement(repuestoRq);
    return ResponseEntity.created(URI.create(String.format("repuestos/%s", repuestoSaved))).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<RepuestoDto> updateReplacement(
      @PathVariable("id") Long id, @Valid @RequestBody RepuestoDto repuestoRq) {
    RepuestoDto repuestoRs = repuestoService.updateReplacement(id, repuestoRq);
    return ResponseEntity.ok(repuestoRs);
  }
}

package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.service.SpareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/repuestos")
@RequiredArgsConstructor
public class SpareDto {

  private final SpareService spareService;

  @PostMapping()
  public ResponseEntity<Long> createReplacement(@Valid @RequestBody com.softdevelop.biomedplus.model.dto.SpareDto repuestoRq) {
    Long repuestoSaved = spareService.createReplacement(repuestoRq);
    return ResponseEntity.created(URI.create(String.format("repuestos/%s", repuestoSaved))).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<com.softdevelop.biomedplus.model.dto.SpareDto> updateReplacement(
      @PathVariable("id") Long id, @Valid @RequestBody com.softdevelop.biomedplus.model.dto.SpareDto repuestoRq) {
    com.softdevelop.biomedplus.model.dto.SpareDto repuestoRs = spareService.updateReplacement(id, repuestoRq);
    return ResponseEntity.ok(repuestoRs);
  }
}

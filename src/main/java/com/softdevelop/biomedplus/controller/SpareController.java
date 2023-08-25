package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.service.SpareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/spares")
@RequiredArgsConstructor
public class SpareController {

  private final SpareService spareService;

  @GetMapping()
  public ResponseEntity<List<SpareDto>> getSpares() {
    List<SpareDto> spares = spareService.getSpares();
    return ResponseEntity.ok(spares);
  }

  @PostMapping()
  public ResponseEntity<SpareDto> createSpare(@Valid @RequestBody SpareDto spareRq) {
    SpareDto resp = spareService.createSpare(spareRq);
    return ResponseEntity.ok(resp);
  }

  @PutMapping("/{id}")
  public ResponseEntity<com.softdevelop.biomedplus.model.dto.SpareDto> updateSpare(
      @PathVariable("id") Long id, @Valid @RequestBody SpareDto spareRq) {
    SpareDto spareRs = spareService.updateSpare(id, spareRq);
    return ResponseEntity.ok(spareRs);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSpare(
      @PathVariable("id") Long id) {
    spareService.deleteSpare(id);
    return ResponseEntity.ok("Spare deleted");
  }
}

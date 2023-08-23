package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.service.SpareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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
  public ResponseEntity<Long> createSpare(@Valid @RequestBody SpareDto spareRq) {
    Long spareSaved = spareService.createSpare(spareRq);
    return ResponseEntity.created(URI.create(String.format("spares/%s", spareSaved))).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<com.softdevelop.biomedplus.model.dto.SpareDto> updateSpare(
      @PathVariable("id") Long id, @Valid @RequestBody SpareDto spareRq) {
    SpareDto spareRs = spareService.updateSpare(id, spareRq);
    return ResponseEntity.ok(spareRs);
  }
}

package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.NextManteinanceEquipementDto;
import com.softdevelop.biomedplus.service.EquipementService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/equipement")
@RequiredArgsConstructor
public class EquipementDto {

  private final EquipementService equipementService;

  @GetMapping()
  public ResponseEntity<List<com.softdevelop.biomedplus.model.dto.EquipementDto>> getProducts() {
    List<com.softdevelop.biomedplus.model.dto.EquipementDto> equipos = equipementService.getProducts();
    return ResponseEntity.ok(equipos);
  }

  @GetMapping("/proximos-mantenimientos")
  public ResponseEntity<List<NextManteinanceEquipementDto>> nextMaintenance() {
    return ResponseEntity.ok(equipementService.nextMaintenanceProducts());
  }

  @PutMapping("/{id}")
  public ResponseEntity<com.softdevelop.biomedplus.model.dto.EquipementDto> updateProducts(
          @PathVariable("id") Long id, @Valid @RequestBody com.softdevelop.biomedplus.model.dto.EquipementDto equipoRq) {
    com.softdevelop.biomedplus.model.dto.EquipementDto equipoRs = equipementService.updateProducts(id, equipoRq);
    return new ResponseEntity<>(equipoRs, HttpStatus.NO_CONTENT);
  }
}

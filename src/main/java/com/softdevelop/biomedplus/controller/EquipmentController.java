package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.NextMaintenanceEquipmentDto;
import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.service.EquipmentService;

import java.net.URI;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

  private final EquipmentService equipmentService;

  @GetMapping()
  public ResponseEntity<List<EquipmentDto>> getEquipments() {
    List<EquipmentDto> equipments = equipmentService.getEquipments();
    return ResponseEntity.ok(equipments);
  }

  @PostMapping()
  public ResponseEntity<Long> createEquipment(@Valid @RequestBody EquipmentDto equipmentRq) {
    Long equipmentSaved = equipmentService.createEquipment(equipmentRq);
    return ResponseEntity.created(URI.create(String.format("equipments/%s", equipmentSaved))).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<EquipmentDto> updateEquipment(
          @PathVariable("id") Long id, @Valid @RequestBody EquipmentDto equipmentRq) {
    EquipmentDto equipmentRs = equipmentService.updateEquipment(id, equipmentRq);
    return ResponseEntity.ok(equipmentRs);
  }
}

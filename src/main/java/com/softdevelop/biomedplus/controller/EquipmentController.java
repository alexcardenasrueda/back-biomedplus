package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.service.EquipmentService;

import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import java.util.List;

import lombok.RequiredArgsConstructor;
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
  public ResponseEntity<EquipmentDto> createEquipment(@Valid @RequestBody EquipmentDto equipmentRq) {
    LoggerEvent.info()
        .forClass(EquipmentController.class)
        .withField("Action: ", "createEquipment")
        .withField("EquipmentRequest", equipmentRq)
        .log();
    EquipmentDto equipment = equipmentService.createEquipment(equipmentRq);
    return ResponseEntity.ok(equipment);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EquipmentDto> updateEquipment(
          @PathVariable("id") Long id, @Valid @RequestBody EquipmentDto equipmentRq) {
    EquipmentDto equipmentRs = equipmentService.updateEquipment(id, equipmentRq);
    return ResponseEntity.ok(equipmentRs);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteEquipment(
      @PathVariable("id") Long id) {
    equipmentService.deleteEquipment(id);
    return ResponseEntity.ok("Equipment deleted");
  }
}

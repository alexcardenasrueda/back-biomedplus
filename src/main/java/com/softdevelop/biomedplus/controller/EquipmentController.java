package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.service.EquipmentService;

import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

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

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<EquipmentDto> createEquipment(@RequestPart("data") EquipmentDto equipmentRq,
      @RequestPart("image")MultipartFile image) {
    LoggerEvent.info()
        .forClass(EquipmentController.class)
        .withField("Action: ", "createEquipment")
        .withField("EquipmentRequest", equipmentRq)
        .log();
    EquipmentDto equipment = equipmentService.createEquipment(equipmentRq, image);
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

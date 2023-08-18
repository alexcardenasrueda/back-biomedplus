package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.MaintenanceDto;
import com.softdevelop.biomedplus.model.dto.NextMaintenanceEquipmentDto;
import com.softdevelop.biomedplus.service.EquipmentService;
import com.softdevelop.biomedplus.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping("/next-expected")
    public ResponseEntity<List<NextMaintenanceEquipmentDto>> nextExpected() {
        return ResponseEntity.ok(maintenanceService.nextExpected());
    }

    @PostMapping()
    public ResponseEntity<Long> createMaintenance(@Valid @RequestBody MaintenanceDto maintenanceRq) {
        Long equipmentSaved = maintenanceService.createMaintenance(maintenanceRq);
        return ResponseEntity.created(URI.create(String.format("maintenances/%s", equipmentSaved))).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceDto> updateMaintenance(
            @PathVariable("id") Long id, @Valid @RequestBody MaintenanceDto maintenanceRq) {
        MaintenanceDto maintenanceRs = maintenanceService.updateMaintenance(id, maintenanceRq);
        return ResponseEntity.ok(maintenanceRs);
    }
}

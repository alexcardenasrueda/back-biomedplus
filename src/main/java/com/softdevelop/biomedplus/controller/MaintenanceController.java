package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.NextMaintenanceEquipmentDto;
import com.softdevelop.biomedplus.service.EquipmentService;
import com.softdevelop.biomedplus.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

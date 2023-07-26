package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.dto.ProximoMantenimientoEquipoDto;
import com.softdevelop.biomedplus.service.EquipoService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class EquipoController {

  private final EquipoService equipoService;

  @GetMapping()
  public ResponseEntity<List<EquipoDto>> getProducts() {
    List<EquipoDto> equipos = equipoService.getProducts();
    return ResponseEntity.ok(equipos);
  }

  @GetMapping("/proximos-mantenimientos")
  public ResponseEntity<List<ProximoMantenimientoEquipoDto>> nextMaintenance() {
    return ResponseEntity.ok(equipoService.nextMaintenanceProducts());
  }

  @PutMapping("/{id}")
  public ResponseEntity<EquipoDto> updateProducts(
          @PathVariable("id") Long id, @Valid @RequestBody EquipoDto equipoRq) {
    EquipoDto equipoRs = equipoService.updateProducts(id, equipoRq);
    return new ResponseEntity<>(equipoRs, HttpStatus.NO_CONTENT);
  }
}

package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.exception.EquipoNotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.service.EquipoService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class EquipoController {

  private final EquipoService equipoService;

  @PutMapping("/{id}")
  public ResponseEntity<EquipoDto> updateProducts(@PathVariable("id") Long id, @Validated @RequestBody EquipoDto equipoRq){
    EquipoDto equipoRs = equipoService.updateProducts(id, equipoRq);
    if (Objects.isNull(equipoRs)) throw new EquipoNotFoundException("id: ".concat(id.toString()));
    return new ResponseEntity<>(equipoRs, HttpStatus.NO_CONTENT);
  }

  @GetMapping()
  public ResponseEntity<EquipoDto> getProducts(){
    EquipoDto equipos = equipoService.getProducts();
    return ResponseEntity.ok(equipos);
  }
}

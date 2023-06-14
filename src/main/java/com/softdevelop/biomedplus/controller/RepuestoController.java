package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.exception.EquipoNotFoundException;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.ProveedorNotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.dto.RepuestoDto;
import com.softdevelop.biomedplus.service.RepuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;


@RestController
@RequestMapping("/repuestos")
@RequiredArgsConstructor
public class RepuestoController {

    private final RepuestoService repuestoService;

    @PostMapping()
    public ResponseEntity<Long> createReplacement(@Validated @RequestBody RepuestoDto repuestoRq) throws ProveedorNotFoundException, GenericException {
        Long repuestoSaved = repuestoService.createReplacement(repuestoRq);
        return ResponseEntity.created(
                        URI.create(String.format("repuestos/%s", repuestoSaved)))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepuestoDto> updateReplacement(@PathVariable("id") Long id,
                                                       @Validated @RequestBody RepuestoDto repuestoRq) throws ProveedorNotFoundException, GenericException {
        RepuestoDto repuestoRs = repuestoService.updateReplacement(id, repuestoRq);
        return ResponseEntity.ok(repuestoRs);
    }
}

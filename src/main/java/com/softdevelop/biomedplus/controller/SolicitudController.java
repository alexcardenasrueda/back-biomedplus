package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.SolicitudDto;
import com.softdevelop.biomedplus.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

  private final SolicitudService solicitudService;

  @GetMapping()
  public ResponseEntity<List<SolicitudDto>> getSolicitudes() {
    List<SolicitudDto> requests = solicitudService.getSolicitudes();
    return ResponseEntity.ok(requests);
  }
}

package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.service.TicketService;
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
public class TicketController {

  private final TicketService ticketService;

  @GetMapping()
  public ResponseEntity<List<TicketDto>> getSolicitudes() {
    List<TicketDto> requests = ticketService.getSolicitudes();
    return ResponseEntity.ok(requests);
  }
}

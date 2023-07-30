package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final TicketService ticketService;

  @GetMapping()
  public ResponseEntity<List<TicketDto>> getTickets() {
    List<TicketDto> requests = ticketService.getTickets();
    return ResponseEntity.ok(requests);
  }

  @PostMapping()
  public ResponseEntity<Long> createTicket(@Valid @RequestBody TicketDto ticketRq) {
    Long equipmentSaved = ticketService.createTicket(ticketRq);
    return ResponseEntity.created(URI.create(String.format("tickets/%s", equipmentSaved))).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<TicketDto> updateEquipment(
          @PathVariable("id") Long id, @Valid @RequestBody TicketDto ticketRq) {
    TicketDto ticketRs = ticketService.updateTicket(id, ticketRq);
    return ResponseEntity.ok(ticketRs);
  }

}

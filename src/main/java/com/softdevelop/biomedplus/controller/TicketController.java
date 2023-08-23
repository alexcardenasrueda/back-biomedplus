package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.service.TicketService;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@Slf4j
public class TicketController {

  private final TicketService ticketService;

  @GetMapping()
  public ResponseEntity<List<TicketDto>> getTickets() {

    LoggerEvent.info()
        .forClass(TicketController.class)
        .withField("Action: ", "getTickets")
        .log();
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

  @GetMapping("/created")
  public ResponseEntity<List<TicketDto>> getTicketsCreated() {
    List<TicketDto> requests = ticketService.getTicketsCreated();
    return ResponseEntity.ok(requests);
  }

}

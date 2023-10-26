package com.softdevelop.biomedplus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.service.TicketService;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

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

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Long> createTicket(@RequestParam("data") String ticketStr,
      @RequestPart("image") MultipartFile image) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    TicketDto ticketRq = objectMapper.readValue(ticketStr, TicketDto.class);
    LoggerEvent.info()
        .forClass(TicketController.class)
        .withField("Action: ", "createTicket")
        .withField("TicketReques", ticketRq)
        .log();
    Long equipmentSaved = ticketService.createTicket(ticketRq, image);
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

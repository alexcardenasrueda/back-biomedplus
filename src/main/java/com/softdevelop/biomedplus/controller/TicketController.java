package com.softdevelop.biomedplus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.service.TicketService;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    LoggerEvent.info().forClass(TicketController.class).withField("Action: ", "getTickets").log();
    List<TicketDto> requests = ticketService.getTickets();
    return ResponseEntity.ok(requests);
  }

  @PostMapping(
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TicketDto> createTicket(
      @RequestParam("data") String ticketStr, @RequestPart("image") MultipartFile image)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    TicketDto ticketRq = objectMapper.readValue(ticketStr, TicketDto.class);
    LoggerEvent.info()
        .forClass(TicketController.class)
        .withField("Action: ", "createTicket")
        .withField("TicketRequest", ticketRq)
        .log();
    TicketDto ticket = ticketService.createTicket(ticketRq, image);
    return ResponseEntity.ok(ticket);
  }

  @PutMapping(
      value = "/{id}",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TicketDto> updateEquipment(
      @PathVariable("id") Long id,
      @RequestParam("data") String ticketStr,
      @RequestPart(value = "image", required = false) MultipartFile image)
      throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    TicketDto ticketRq = objectMapper.readValue(ticketStr, TicketDto.class);
    TicketDto ticketRs = ticketService.updateTicket(id, ticketRq, image);
    return ResponseEntity.ok(ticketRs);
  }

  @GetMapping("/created")
  public ResponseEntity<List<TicketDto>> getTicketsCreated() {
    List<TicketDto> requests = ticketService.getTicketsCreated();
    return ResponseEntity.ok(requests);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTicket(
      @PathVariable("id") Long id) {
    ticketService.deleteTicket(id);
    return ResponseEntity.ok("Ticket deleted");
  }
}

package com.softdevelop.biomedplus.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.service.SpareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/spares")
@RequiredArgsConstructor
public class SpareController {

  private final SpareService spareService;

  @GetMapping()
  public ResponseEntity<List<SpareDto>> getSpares() {
    List<SpareDto> spares = spareService.getSpares();
    return ResponseEntity.ok(spares);
  }

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<SpareDto> createSpare(@RequestParam("data") String spareStr,
      @RequestPart("image") MultipartFile image) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    SpareDto spareRq = objectMapper.readValue(spareStr, SpareDto.class);
    SpareDto resp = spareService.createSpare(spareRq, image);
    return ResponseEntity.ok(resp);
  }

  @PutMapping(value = "/{id}",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<SpareDto> updateSpare(
      @PathVariable("id") Long id, @RequestParam("data") String spareStr,
      @RequestPart("image")MultipartFile image) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    SpareDto spareRq = objectMapper.readValue(spareStr, SpareDto.class);
    SpareDto spareRs = spareService.updateSpare(id, spareRq, image);
    return ResponseEntity.ok(spareRs);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSpare(
      @PathVariable("id") Long id) {
    spareService.deleteSpare(id);
    return ResponseEntity.ok("Spare deleted");
  }
}

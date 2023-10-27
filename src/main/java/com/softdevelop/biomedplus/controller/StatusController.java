package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.StatusDto;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.service.StatusService;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {

  private final StatusService statusService;

  @GetMapping("/{id}")
  public ResponseEntity<StatusDto> getStatusById(
      @PathVariable("id") Long id) {
    LoggerEvent.info()
        .forClass(StatusController.class)
        .withField("Action: ", "getStatusById")
        .withField("Id", id)
        .log();
    StatusDto response = statusService.getStatusById(id);
    return ResponseEntity.ok(response);
  }
}

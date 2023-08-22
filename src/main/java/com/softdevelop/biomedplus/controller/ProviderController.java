package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.service.ProviderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

  private final ProviderService providerService;

  @GetMapping()
  public ResponseEntity<List<ProviderDto>> getProviders() {
    List<ProviderDto> providers = providerService.getProviders();
    return ResponseEntity.ok(providers);
  }
}

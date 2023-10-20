package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.service.ProviderService;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping()
  public ResponseEntity<ProviderDto> createProvider(@Valid @RequestBody ProviderDto providerRq) {
    LoggerEvent.info()
        .forClass(ProviderController.class)
        .withField("Action: ", "createProvider")
        .withField("ProvidertRequest", providerRq)
        .log();
    ProviderDto provider = providerService.createProvider(providerRq);
    return ResponseEntity.ok(provider);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProviderDto> updateProvider(
      @PathVariable("id") Long id, @Valid @RequestBody ProviderDto providerRq) {
    ProviderDto providerRs = providerService.updateProvider(id, providerRq);
    return ResponseEntity.ok(providerRs);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProvider(
      @PathVariable("id") Long id) {
    providerService.deleteProvider(id);
    return ResponseEntity.ok("Provider deleted");
  }
}

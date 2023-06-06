package com.softdevelop.biomedplus.controller;

import com.softdevelop.biomedplus.model.dto.EquipoRq;
import com.softdevelop.biomedplus.service.ProductService;
import com.softdevelop.biomedplus.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class EquipoController {

  private final ProductService productService;

  @PutMapping("/update")
  public ResponseEntity<GenericResponse> updateProducts(@Validated @RequestBody EquipoRq equipoRq){
    try {
      productService.updateProducts(equipoRq);
      return new ResponseEntity<>(GenericResponse.builder().build(), HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(GenericResponse.builder().errorCode(" ").errorMessage(" ").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

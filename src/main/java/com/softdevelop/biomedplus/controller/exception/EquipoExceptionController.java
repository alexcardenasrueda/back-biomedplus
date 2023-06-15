package com.softdevelop.biomedplus.controller.exception;

import com.softdevelop.biomedplus.exception.EquipoNotFoundException;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EquipoExceptionController {

  @ExceptionHandler(value = EquipoNotFoundException.class)

  public ResponseEntity<Object> ex(EquipoNotFoundException exception) {
    return new ResponseEntity<>(String.format("Equipo no encontrado %s", exception.getMessage()),
        HttpStatus.NOT_FOUND);
  }
}



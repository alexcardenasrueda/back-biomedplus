package com.softdevelop.biomedplus.controller.exception;

import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<ErrorDto> notFoundException(NotFoundException exception) {
    ErrorDto error = ErrorDto.builder()
            .errorCode(HttpStatus.NOT_FOUND.toString())
            .errorMessage(exception.getMessage())
            .build();
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = GenericException.class)
  public ResponseEntity<ErrorDto> genericException(GenericException exception) {
    ErrorDto error = ErrorDto.builder()
            .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .errorMessage(exception.getMessage())
            .build();
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });
    return errors;
  }
}



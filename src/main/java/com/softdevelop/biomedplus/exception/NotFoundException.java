package com.softdevelop.biomedplus.exception;

public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = -3160698027617016500L;

  public NotFoundException(String message) {
    super(message);
  }
}

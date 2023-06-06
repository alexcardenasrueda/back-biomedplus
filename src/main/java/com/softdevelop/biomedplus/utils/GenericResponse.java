package com.softdevelop.biomedplus.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
public class GenericResponse {

  private String httpStatus;
  private String errorCode;
  private String errorMessage;
}

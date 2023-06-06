package com.softdevelop.biomedplus.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ProveedorDto {

  private Long id;
  private String nombre;
  private String telefono;
  private String dirección;
}

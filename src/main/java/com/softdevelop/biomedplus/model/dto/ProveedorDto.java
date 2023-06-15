package com.softdevelop.biomedplus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDto {

  private Long id;
  private String nombre;
  private String telefono;
  private String ciudad;
  private String direccion;
}

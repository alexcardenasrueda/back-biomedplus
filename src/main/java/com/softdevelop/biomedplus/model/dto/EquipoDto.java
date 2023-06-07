package com.softdevelop.biomedplus.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class EquipoDto {
  private String nombre;
  private String marca;
  private String modelo;
  private String serie;
  private String servicio;
  private String area;
  private String numeroActivo;
  private String accesorios;
  private String tipoEquipo;
  private ProveedorDto proveedor;
}

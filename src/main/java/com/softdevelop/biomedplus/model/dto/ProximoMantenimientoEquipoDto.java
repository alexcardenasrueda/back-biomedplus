package com.softdevelop.biomedplus.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProximoMantenimientoEquipoDto {
  private Long id;
  private String nombre;
  private String marca;
  private String modelo;
  private String serie;
  private String servicio;
  private String area;
  private String tipoEquipo;
  private String fechaProximoMantenimiento;
}

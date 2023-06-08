package com.softdevelop.biomedplus.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RepuestoDto {
    private String nombre;
    private String marca;
    private String modelo;
    private String serie;
    private String servicio;
    private String item;
    private String referencia;
    private Integer cantidad;
    private Long precio;
    private Long proveedorID;
}

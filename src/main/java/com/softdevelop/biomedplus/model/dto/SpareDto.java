package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpareDto {
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

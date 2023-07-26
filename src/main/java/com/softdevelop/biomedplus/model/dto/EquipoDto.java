package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EquipoDto implements Serializable {

  private static final long serialVersionUID = 511137409216173558L;

  private Long id;

  @NotBlank(message = "El nombre no puede ser vacio")
  private String nombre;

  @NotBlank(message = "La marca no puede ser vacia")
  private String marca;

  @NotBlank(message = "El modelo no puede ser vacio")
  private String modelo;

  @NotBlank(message = "La serie no puede ser vacia")
  private String serie;

  @NotBlank(message = "El servicio no puede ser vacio")
  private String servicio;

  @NotBlank(message = "El área no puede ser vacio")
  private String area;

  @NotBlank(message = "El número de activo no puede ser vacio")
  private String numeroActivo;

  @NotBlank(message = "El valor de accesorios no puede ser vacio")
  private String accesorios;

  @NotBlank(message = "El tipo de equipo no puede ser vacio")
  private String tipoEquipo;

  @NotNull private ProveedorDto proveedor;
}

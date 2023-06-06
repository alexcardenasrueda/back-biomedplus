package com.softdevelop.biomedplus.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipos")
@Entity
public class EquipoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String marca;
  private String modelo;
  private String serie;
  private String servicio;
  private String area;
  private String numeroActivo;
  private String accesorios;
  private String tipoEquipo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
  private ProveedorEntity proveedor;
}

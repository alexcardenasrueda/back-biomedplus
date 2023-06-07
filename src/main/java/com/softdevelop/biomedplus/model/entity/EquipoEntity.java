package com.softdevelop.biomedplus.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "equipos")
@Entity
public class EquipoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "nombre")
  private String nombre;
  @Column(name = "marca")
  private String marca;
  @Column(name = "modelo")
  private String modelo;
  @Column(name = "serie")
  private String serie;
  @Column(name = "servicio")
  private String servicio;
  @Column(name = "area")
  private String area;
  @Column(name = "numero_activo")
  private String numeroActivo;
  @Column(name = "accesorios")
  private String accesorios;
  @Column(name = "tipo_equipo")
  private String tipoEquipo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
  private ProveedorEntity proveedor;
}

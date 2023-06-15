package com.softdevelop.biomedplus.model.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "proveedores")
@Entity
public class ProveedorEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "telefono")

  private String telefono;

  @Column(name = "ciudad")

  private String ciudad;

  @Column(name = "direccion")

  private String direccion;

  @OneToOne(mappedBy = "proveedor")
  private EquipoEntity equipo;

  @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY,
          cascade = CascadeType.ALL)
  private List<RepuestoEntity> repuestoList;
}

package com.softdevelop.biomedplus.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "repuestos")
@Entity
public class RepuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "item")
    private String item;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "serie")
    private String serie;
    @Column(name = "servicio")
    private String servicio;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio")
    private Long precio;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
//    private ProveedorEntity proveedor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name = "FK_REPUESTOS_PROVEEDORES"))
    private ProveedorEntity proveedor;
}

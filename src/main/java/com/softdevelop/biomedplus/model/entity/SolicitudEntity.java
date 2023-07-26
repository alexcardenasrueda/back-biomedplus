package com.softdevelop.biomedplus.model.entity;

import com.softdevelop.biomedplus.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "solicitudes")
@Entity
public class SolicitudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    @Column(name = "fecha_cierre")
    private String fechaCierre;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "estado")
    private Status id_estado;
    @Column(name = "equipo")
    private Long id_equipo;
    @Column(name = "usuario")
    private Long id_usuario;
}

package com.softdevelop.biomedplus.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mantenimientos")
@Entity
public class MantenimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fechaEstimada")
    private LocalDate fechaEstimada;
    @Column(name = "fechaEjecutada")
    private LocalDate fechaEjecutada;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_equipo", nullable = false, foreignKey = @ForeignKey(name = "FK_MANTENIMIENTOS_EQUIPOS"))
    private EquipoEntity equipo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_estado", nullable = false, foreignKey = @ForeignKey(name = "FK_MANTENIMIENTOS_ESTADOS"))
    private EstadoEntity estado;
}

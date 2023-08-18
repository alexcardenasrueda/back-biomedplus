package com.softdevelop.biomedplus.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "maintenance")
@Entity
public class MaintenanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "estimated_date")
    private String estimatedDate;
    @Column(name = "done_date")
    private String doneDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_equipment", nullable = false, foreignKey = @ForeignKey(name = "FK_MAINTENANCE_EQUIPMENT"))
    private EquipmentEntity equipment;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_status", nullable = false, foreignKey = @ForeignKey(name = "FK_MAINTENANCE_STATUS"))
    private StatusEntity status;
}

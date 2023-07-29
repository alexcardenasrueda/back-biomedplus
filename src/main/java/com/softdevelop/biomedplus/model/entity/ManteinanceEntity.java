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
public class ManteinanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "estimated_date")
    private LocalDate estimatedDate;
    @Column(name = "done_date")
    private LocalDate doneDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_equipement", nullable = false, foreignKey = @ForeignKey(name = "FK_MANTEINANCE_EQUIPEMENT"))
    private EquipementEntity equipement;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_status", nullable = false, foreignKey = @ForeignKey(name = "FK_MANTEINANCE_STATUS"))
    private StatusEntity status;
}

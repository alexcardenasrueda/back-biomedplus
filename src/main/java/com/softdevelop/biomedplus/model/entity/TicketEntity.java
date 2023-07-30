package com.softdevelop.biomedplus.model.entity;

import com.softdevelop.biomedplus.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ticket")
@Entity
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "creation_date")
    private String creationDate;
    @Column(name = "close_date")
    private String closeDate;
    @Column(name = "image")
    private String image;

    @OneToOne()
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private StatusEntity status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_equipment", nullable = false, foreignKey = @ForeignKey(name = "FK_TICKET_EQUIPMENT"))
    private EquipmentEntity equipment;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_TICKET_USER"))
    private UserEntity user;
}

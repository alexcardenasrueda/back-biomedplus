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

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ticket")
@Entity
public class TickerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descritcion")
    private String description;
    @Column(name = "creation_date")
    private String creationDate;
    @Column(name = "close_date")
    private String closeDate;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    private Status idStatus;
    @Column(name = "equipement")
    private Long idEquipement;
    @Column(name = "user")
    private Long idUser;
}

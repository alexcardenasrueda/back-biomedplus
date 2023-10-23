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
@Table(name = "equipment")
@Entity
public class EquipmentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "brand")
  private String brand;

  @Column(name = "model")
  private String model;

  @Column(name = "series")
  private String series;

  @Column(name = "service")
  private String service;

  @Column(name = "area")
  private String area;

  @Column(name = "active_number")
  private String activeNumber;

  @Column(name = "accessories")
  private String accessories;

  @Column(name = "equipment_type")
  private String equipmentType;

  @Column(name = "image")
  private String image;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "id_provider", nullable = false, foreignKey = @ForeignKey(name = "FK_EQUIPMENT_PROVIDER"))
  private ProviderEntity provider;

  @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<MaintenanceEntity> maintenances;

  @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<TicketEntity> ticketList;
}

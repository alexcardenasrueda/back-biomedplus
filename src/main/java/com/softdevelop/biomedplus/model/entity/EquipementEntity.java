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
@Table(name = "equipement")
@Entity
public class EquipementEntity {

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

  @Column(name = "equipement_type")
  private String equipementType;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_provider", referencedColumnName = "id")
  private ProviderEntity provider;

  @OneToMany(mappedBy = "equipement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ManteinanceEntity> maintenance;
}

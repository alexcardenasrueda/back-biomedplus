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
@Table(name = "provider")
@Entity
public class ProviderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "phone")
  private String phone;

  @Column(name = "city")
  private String city;

  @Column(name = "address")
  private String address;

  @OneToOne(mappedBy = "provider")
  private EquipementEntity equipement;

  @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY,
          cascade = CascadeType.ALL)
  private List<SpareEntity> spareList;
}

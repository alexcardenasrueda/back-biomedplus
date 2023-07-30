package com.softdevelop.biomedplus.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "spare")
@Entity
public class SpareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "item")
    private String item;
    @Column(name = "code_reference")
    private String codeReference;
    @Column(name = "series")
    private String series;
    @Column(name = "service")
    private String service;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Long price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_provider", nullable = false, foreignKey = @ForeignKey(name = "FK_SPARE_PROVIDER"))
    private ProviderEntity provider;
}

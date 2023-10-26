package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpareDto {

    private Long id;
    private String name;
    private String brand;
    private String model;
    private String series;
    private String service;
    private String item;
    private String codeReference;
    private Integer quantity;
    private Long price;
    private String image;

    @NotNull(message = "The provider of equipment cannot be empty")
    private ProviderDto provider;
}

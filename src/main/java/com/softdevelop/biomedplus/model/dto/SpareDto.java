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
    private String name;
    private String brand;
    private String model;
    private String series;
    private String service;
    private String item;
    private String reference;
    private Integer quantity;
    private Long price;

    @NotNull(message = "The provider of equipment cannot be empty")
    private ProviderDto provider;
}

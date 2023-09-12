package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softdevelop.biomedplus.model.entity.MaintenanceEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EquipmentDto implements Serializable {

  private static final long serialVersionUID = 511137409216173558L;

  private Long id;

  @NotBlank(message = "The equipment name cannot be empty")
  private String name;

  @NotBlank(message = "The equipment brand cannot be empty")
  private String brand;

  @NotBlank(message = "The equipment model cannot be empty")
  private String model;

  @NotBlank(message = "The equipment series cannot be empty")
  private String series;

  @NotBlank(message = "The equipment service cannot be empty")
  private String service;

  @NotBlank(message = "The equipment area cannot be empty")
  private String area;

  @NotBlank(message = "The equipment active number cannot be empty")
  private String activeNumber;

  @NotBlank(message = "The equipment accessories cannot be empty")
  private String accessories;

  @NotBlank(message = "The type of equipment cannot be empty")
  private String equipmentType;

  private ProviderDto provider;

  private MaintenanceDto nextMaintenance;
}

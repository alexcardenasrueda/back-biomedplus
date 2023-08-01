package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NextMaintenanceEquipmentDto {
  private Long id;
  private String name;
  private String brand;
  private String model;
  private String series;
  private String service;
  private String area;
  private String equipmentType;
  private String nextMaintenanceDate;
}

package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.StatusEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MaintenanceDto implements Serializable {

  private static final long serialVersionUID = 511137409216173558L;

  private Long id;

  @NotBlank(message = "The maintenance estimated_date cannot be empty")
  private String estimatedDate;

  private String doneDate;

  private StatusDto status;

  @NotNull(message = "The maintenance equipment cannot be empty")
  private EquipmentDto equipment;


}

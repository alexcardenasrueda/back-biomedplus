package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TicketDto implements Serializable {
  private static final long serialVersionUID = -2726183053018213672L;

  private long id;

  @NotBlank(message = "The ticket description cannot be empty")
  private String description;

  @NotBlank(message = "The ticket creation_date cannot be empty")
  private String creationDate;

  private String closeDate;

  @NotNull(message = "The ticket status cannot be empty")
  private StatusDto status;

  @NotNull(message = "The ticket equipment cannot be empty")
  private EquipmentDto equipment;

  @NotNull(message = "The ticket user cannot be empty")
  private UserDto user;

  private String image;
}

package com.softdevelop.biomedplus.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.softdevelop.biomedplus.enums.Status;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TicketDto implements Serializable {
    private static final long serialVersionUID = -2726183053018213672L;

    @NotBlank(message = "The ticket description cannot be empty")
    private String description;

    @NotBlank(message = "The ticket creation_date cannot be empty")
    private String creationDate;

    private String closeDate;

    @NotBlank(message = "The ticket img cannot be empty")
    private String image;

    @NotNull(message = "The ticket status cannot be empty")
    private StatusDto status;

    @NotNull(message = "The ticket equipment cannot be empty")
    private EquipmentDto equipment;

    @NotNull(message = "The ticket user cannot be empty")
    private UserDto user;
}

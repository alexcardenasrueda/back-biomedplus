package com.softdevelop.biomedplus.service.translator;

import static com.softdevelop.biomedplus.util.Constants.TICKET_IMAGE_DIRECTORY;

import com.softdevelop.biomedplus.enums.Status;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.StatusDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.model.entity.*;
import com.softdevelop.biomedplus.util.GenericUtilities;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketTranslator {

  private final GenericUtilities genericUtilities;

  public TicketEntity setTicketDtoToTicketEntity(TicketEntity ticketEntity, TicketDto ticketDto) {
    ticketEntity.setDescription(ticketDto.getDescription().toUpperCase());
    ticketEntity.setCreationDate(ticketDto.getCreationDate());
    ticketEntity.setCloseDate(ticketDto.getCloseDate());

    ticketEntity.setStatus(StatusEntity.builder().id(ticketDto.getStatus().getId()).build());
    ticketEntity.setEquipment(EquipmentEntity.builder().id(ticketDto.getEquipment().getId()).build());
    ticketEntity.setUser(UserEntity.builder().id(ticketDto.getUser().getId()).build());

    return ticketEntity;
  }

  public TicketDto setTicketEntityToTicketDto(TicketEntity ticket) {
    // Set image
    String imageToResponse = null;
    String closeDate = null;
    if (ticket.getImage() != null && !ticket.getImage().isEmpty()) {
      imageToResponse =
          genericUtilities.readImageFromServer(TICKET_IMAGE_DIRECTORY, ticket.getImage());
    }
    if (Objects.nonNull(ticket.getCloseDate())) {
      closeDate = ticket.getCloseDate();
    }

    return TicketDto.builder()
        .id(ticket.getId())
        .description(ticket.getDescription().toUpperCase())
        .creationDate(ticket.getCreationDate())
        .closeDate(closeDate)
        .image(imageToResponse)

        .status(StatusDto.builder()
            .id(ticket.getStatus().getId())
            .name(Status.GetStatusByName(ticket.getStatus().getName()))
            .build())

        .equipment(EquipmentDto.builder()
            .id(ticket.getEquipment().getId())
            .name(ticket.getEquipment().getName())
            .brand(ticket.getEquipment().getBrand())
            .model(ticket.getEquipment().getModel())
            .series(ticket.getEquipment().getSeries())
            .activeNumber(ticket.getEquipment().getActiveNumber())
            .area(ticket.getEquipment().getArea())
            .service(ticket.getEquipment().getService())
            .build())

        .user(UserDto.builder()
            .id(ticket.getUser().getId())
            .name(ticket.getUser().getName())
            .build())
        .build();
  }
}

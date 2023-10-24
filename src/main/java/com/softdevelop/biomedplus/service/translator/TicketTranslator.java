package com.softdevelop.biomedplus.service.translator;

import static com.softdevelop.biomedplus.util.Constants.EQUIPMENT_IMAGE_DIRECTORY;
import static com.softdevelop.biomedplus.util.Constants.TICKET_IMAGE_DIRECTORY;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.SpareDto;
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

  public TicketEntity setTicketDtoToTicketEntity (TicketEntity ticketEntity, TicketDto ticketDto){
      ticketEntity.setDescription(ticketDto.getDescription().toUpperCase());
      ticketEntity.setCreationDate(ticketDto.getCreationDate());
      ticketEntity.setCloseDate(ticketDto.getCloseDate());
      StatusEntity status = new StatusEntity();
      status.setId(ticketDto.getStatus().getId());
      ticketEntity.setStatus(status);
      EquipmentEntity equipment = new EquipmentEntity();
      equipment.setId(ticketDto.getEquipment().getId());
      ticketEntity.setEquipment(equipment);
      UserEntity user = new UserEntity();
      user.setId(ticketDto.getUser().getId());
      ticketEntity.setUser(user);
      return ticketEntity;
  }

    public TicketDto setTicketEntityToTicketDto(TicketEntity ticket) {
      // Set image
      String imageToResponse = null;
      String closeDate = null;
      if (ticket.getImage() != null && !ticket.getImage().isEmpty()){
        imageToResponse = genericUtilities.readImageFromServer(TICKET_IMAGE_DIRECTORY,
            ticket.getImage());
      }
      if (Objects.nonNull(ticket.getCloseDate())){
        closeDate = ticket.getCloseDate();
      }

      return TicketDto.builder()
          .id(ticket.getId())
          .description(ticket.getDescription())
          .creationDate(ticket.getCreationDate())
          .closeDate(closeDate)
          .image(imageToResponse)
          .status(StatusDto.builder().id(ticket.getStatus().getId()).build())
          .equipment(EquipmentDto.builder().id(ticket.getEquipment().getId()).build())
          .user(UserDto.builder().id(ticket.getUser().getId()).build())
          .build();
    }
}

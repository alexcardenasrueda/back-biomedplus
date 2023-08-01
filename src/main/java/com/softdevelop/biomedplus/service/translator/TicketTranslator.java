package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketTranslator {

  public TicketEntity setTicketDtoToTicketEntity (TicketEntity ticketEntity, TicketDto ticketDto){
      ticketEntity.setDescription(ticketDto.getDescription().toUpperCase());
      ticketEntity.setCreationDate(ticketDto.getCreationDate());
      ticketEntity.setCloseDate(ticketDto.getCloseDate());
      ticketEntity.setImage(ticketDto.getImage());
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
}

package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import com.softdevelop.biomedplus.model.entity.TicketEntity;
import com.softdevelop.biomedplus.repository.StatusRepository;
import com.softdevelop.biomedplus.repository.UserRepository;
import com.softdevelop.biomedplus.service.TicketService;
import com.softdevelop.biomedplus.repository.EquipmentRepository;
import com.softdevelop.biomedplus.repository.TicketRepository;
import com.softdevelop.biomedplus.service.translator.EquipmentTranslator;
import com.softdevelop.biomedplus.service.translator.TicketTranslator;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EquipmentRepository equipmentRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TicketTranslator ticketTranslator;

    @Override
    public List<TicketDto> getTickets() throws GenericException {

        List<TicketDto> ticketsRs = new ArrayList<>();
        try {
            List<TicketEntity> allTickets = (List<TicketEntity>) ticketRepository.findAll();
      if (allTickets.isEmpty()) {
                throw new NotFoundException("Tickets not found");
            }
            ticketsRs = modelMapper.map(allTickets, new TypeToken<List<TicketDto>>() {
            }.getType());
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return ticketsRs;
    }

    @Override
    public Long createTicket(TicketDto ticketDto) {
        long idTicket;
        try{
            Boolean exist = equipmentRepository.existsById(ticketDto.getEquipment().getId());
            if (!exist) {
                throw new NotFoundException("Equipment not found");
            }

            exist = statusRepository.existsById(ticketDto.getStatus().getId());
            if (!exist) {
                throw new NotFoundException("Status not found");
            }

            exist = userRepository.existsById(ticketDto.getUser().getId());
            if (!exist) {
                throw new NotFoundException("User not found");
            }

            TicketEntity ticketEntity = new TicketEntity();
            TicketEntity ticketSaved = ticketRepository.save(
                    ticketTranslator.setTicketDtoToTicketEntity(ticketEntity, ticketDto));
            idTicket = ticketSaved.getId();
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return idTicket;
    }

    @Override
    public TicketDto updateTicket(Long id, TicketDto ticketDto) {
        TicketDto ticketSavedDto;
        try{
            Boolean exist = ticketRepository.existsById(id);
            if (!exist) {
                throw new NotFoundException("Ticket not found");
            }

            exist = equipmentRepository.existsById(ticketDto.getEquipment().getId());
            if (!exist) {
                throw new NotFoundException("Equipment not found");
            }

            exist = statusRepository.existsById(ticketDto.getStatus().getId());
            if (!exist) {
                throw new NotFoundException("Status not found");
            }

            exist = userRepository.existsById(ticketDto.getUser().getId());
            if (!exist) {
                throw new NotFoundException("User not found");
            }

            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setId(id);
            TicketEntity ticketSaved = ticketRepository.save(
                    ticketTranslator.setTicketDtoToTicketEntity(ticketEntity, ticketDto));
            ticketSavedDto = modelMapper.map(ticketSaved, TicketDto.class);
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return ticketSavedDto;

    }
}

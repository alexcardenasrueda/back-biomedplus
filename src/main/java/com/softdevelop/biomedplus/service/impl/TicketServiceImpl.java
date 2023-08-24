package com.softdevelop.biomedplus.service.impl;

import static com.softdevelop.biomedplus.util.Constants.NOT_FOUND_TICKETS;

import com.softdevelop.biomedplus.enums.Status;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.model.entity.TicketEntity;
import com.softdevelop.biomedplus.repository.StatusRepository;
import com.softdevelop.biomedplus.repository.UserRepository;
import com.softdevelop.biomedplus.service.TicketService;
import com.softdevelop.biomedplus.repository.EquipmentRepository;
import com.softdevelop.biomedplus.repository.TicketRepository;
import com.softdevelop.biomedplus.service.translator.TicketTranslator;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EquipmentRepository equipmentRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final TicketTranslator ticketTranslator;

    @Override
    public List<TicketDto> getTickets() throws GenericException {

        LoggerEvent.info()
            .forClass(TicketServiceImpl.class)
            .withField("Action: ", "getAllTickets")
            .log();

        List<TicketDto> ticketsRs;
        try {
            List<TicketEntity> allTickets = ticketRepository.findAll();
      if (allTickets.isEmpty()) {
          log.error(NOT_FOUND_TICKETS);
                throw new NotFoundException(NOT_FOUND_TICKETS);
            }
            ticketsRs = modelMapper.map(allTickets, new TypeToken<List<TicketDto>>() {
            }.getType());
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return ticketsRs;
    }

    @Override
    public List<TicketDto> getTicketsCreated() throws GenericException {
        LoggerEvent.info()
            .forClass(TicketServiceImpl.class)
            .withField("Action: ", "getTicketsCreated")
            .log();

        List<TicketDto> ticketsRs;
        try {
            List<TicketEntity> allTickets = ticketRepository.findByStatusName(Status.CREATED.name());

            if (allTickets.isEmpty()) {
                log.error(NOT_FOUND_TICKETS);
                throw new NotFoundException(NOT_FOUND_TICKETS);
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
            boolean exist = equipmentRepository.existsById(ticketDto.getEquipment().getId());
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
            boolean exist = ticketRepository.existsById(id);
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

package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.NextMaintenanceEquipmentDto;
import com.softdevelop.biomedplus.model.dto.TicketDto;

import java.util.List;

public interface TicketService {
    List<TicketDto> getTickets() throws GenericException;
    List<TicketDto> getTicketsCreated() throws GenericException;
    Long createTicket(TicketDto ticketDto);

    TicketDto updateTicket(Long id, TicketDto ticketDto);

}

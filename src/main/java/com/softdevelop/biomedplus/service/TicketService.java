package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.TicketDto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface TicketService {
  List<TicketDto> getTickets() throws GenericException;

  List<TicketDto> getTicketsCreated() throws GenericException;

  TicketDto createTicket(TicketDto ticketDto, MultipartFile image);

  TicketDto updateTicket(Long id, TicketDto ticketDto, MultipartFile image);
}

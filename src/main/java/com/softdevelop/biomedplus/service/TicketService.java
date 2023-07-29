package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.TicketDto;

import java.util.List;

public interface TicketService {
    List<TicketDto> getSolicitudes() throws GenericException;
}

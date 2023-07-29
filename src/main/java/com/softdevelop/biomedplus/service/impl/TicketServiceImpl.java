package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.TicketDto;
import com.softdevelop.biomedplus.model.entity.TickerEntity;
import com.softdevelop.biomedplus.service.TicketService;
import com.softdevelop.biomedplus.repository.EquipementRepository;
import com.softdevelop.biomedplus.repository.TickerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {


    private final TickerRepository tickerRepository;
    private final EquipementRepository equipementRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<TicketDto> getSolicitudes() throws GenericException {

        List<TicketDto> solicitudesRs = new ArrayList<>();
        try {
            List<TickerEntity> solicitudesEntityList = (List<TickerEntity>) tickerRepository.findAll();
      if (solicitudesEntityList.isEmpty()) {
                throw new NotFoundException("Solicitud not found");
            }
            solicitudesRs = modelMapper.map(solicitudesEntityList, new TypeToken<List<TicketDto>>() {
            }.getType());
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return solicitudesRs;
    }
}

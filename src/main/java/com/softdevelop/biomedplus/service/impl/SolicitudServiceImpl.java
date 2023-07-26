package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.SolicitudDto;
import com.softdevelop.biomedplus.model.entity.SolicitudEntity;
import com.softdevelop.biomedplus.service.SolicitudService;
import com.softdevelop.biomedplus.repository.EquipoRepository;
import com.softdevelop.biomedplus.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {


    private final SolicitudRepository solicitudRepository;
    private final EquipoRepository equipoRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<SolicitudDto> getSolicitudes() throws GenericException {

        List<SolicitudDto> solicitudesRs = new ArrayList<>();
        try {
            List<SolicitudEntity> solicitudesEntityList = (List<SolicitudEntity>) solicitudRepository.findAll();
      if (solicitudesEntityList.isEmpty()) {
                throw new NotFoundException("Solicitud not found");
            }
            solicitudesRs = modelMapper.map(solicitudesEntityList, new TypeToken<List<SolicitudDto>>() {
            }.getType());
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return solicitudesRs;
    }
}

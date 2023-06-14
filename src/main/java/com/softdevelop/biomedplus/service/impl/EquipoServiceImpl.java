package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.EquipoNotFoundException;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import com.softdevelop.biomedplus.repository.EquipoRepository;
import com.softdevelop.biomedplus.service.EquipoService;
import com.softdevelop.biomedplus.service.translator.EquipoTranslator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {

  private final EquipoRepository repository;
  private final EquipoTranslator equipoTranslator;

  private final ModelMapper modelMapper;

  @Override
  public EquipoDto updateProducts(Long id, EquipoDto equipoRq) {

    Optional<EquipoEntity> equipoEntity = repository.findById(id);
    if (equipoEntity.isEmpty()) {
      return null;
    }
    EquipoEntity equipoUpdated = repository.save(
        equipoTranslator.setEquipoDtoToEquipoEntity(equipoEntity.get(), equipoRq));
    return modelMapper.map(equipoUpdated, EquipoDto.class);
  }

  @Override
  public List<EquipoDto> getProducts() throws GenericException {
    List<EquipoDto> equipos = new ArrayList<>();

    try {
      List<EquipoEntity> allequipos = (List<EquipoEntity>) repository.findAll();
      if (allequipos == null || allequipos.isEmpty()) {
        throw new EquipoNotFoundException("Equipos not found");
      }
      equipos = modelMapper.map(allequipos, new TypeToken<List<EquipoDto>>() {
      }.getType());
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return equipos;
  }
}

package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import com.softdevelop.biomedplus.repository.EquipoRepository;
import com.softdevelop.biomedplus.service.EquipoService;
import com.softdevelop.biomedplus.service.translator.EquipoTranslator;
import com.softdevelop.biomedplus.service.translator.ProveedorTranslator;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
  public EquipoDto getProducts() {
    List<EquipoEntity> equipos = (List<EquipoEntity>) repository.findAll();
    return null;
  }

}

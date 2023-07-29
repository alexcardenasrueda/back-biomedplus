package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipementDto;
import com.softdevelop.biomedplus.model.dto.NextManteinanceEquipementDto;
import com.softdevelop.biomedplus.model.entity.EquipementEntity;
import com.softdevelop.biomedplus.model.entity.ManteinanceEntity;
import com.softdevelop.biomedplus.repository.EquipementRepository;
import com.softdevelop.biomedplus.repository.MaintenanceRepository;
import com.softdevelop.biomedplus.service.EquipementService;
import com.softdevelop.biomedplus.service.translator.EquipementTranslator;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements EquipementService {

  private final EquipementRepository repository;
  private final MaintenanceRepository maintenanceRepository;
  private final EquipementTranslator equipementTranslator;

  private final ModelMapper modelMapper;

  @Override
  public EquipementDto updateProducts(Long id, EquipementDto equipementRq) {

    Optional<EquipementEntity> equipememtEntity = repository.findById(id);
    if (equipememtEntity.isEmpty()) {
      return null;
    }
    EquipementEntity equipoUpdated = repository.save(
        equipementTranslator.setEquipoDtoToEquipoEntity(equipememtEntity.get(), equipementRq));
    return modelMapper.map(equipoUpdated, EquipementDto.class);
  }

  @Override
  public List<EquipementDto> getProducts() throws GenericException {
    List<EquipementDto> equipos = new ArrayList<>();

    try {
      List<EquipementEntity> allequipos = (List<EquipementEntity>) repository.findAll();
      if (allequipos == null || allequipos.isEmpty()) {
        throw new NotFoundException("Equipos not found");
      }
      equipos = modelMapper.map(allequipos, new TypeToken<List<EquipementDto>>() {
      }.getType());
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return equipos;
  }

  @Override
  public List<NextManteinanceEquipementDto> nextMaintenanceProducts() {
    List<ManteinanceEntity> mantenimientos = maintenanceRepository.
            findByEstimatedDateLessThanEqualAndDoneDateIsNull(LocalDate.now().plusDays(30));
    List<NextManteinanceEquipementDto> proximosMantenimientos = new ArrayList<NextManteinanceEquipementDto>();
    for (ManteinanceEntity mantenimiento: mantenimientos) {
        proximosMantenimientos.add(new NextManteinanceEquipementDto(mantenimiento.getEquipement().getId(),
                mantenimiento.getEquipement().getName(),
                mantenimiento.getEquipement().getBrand(),
                mantenimiento.getEquipement().getModel(),
                mantenimiento.getEquipement().getSeries(),
                mantenimiento.getEquipement().getService(),
                mantenimiento.getEquipement().getArea(),
                mantenimiento.getEquipement().getEquipementType(),
                mantenimiento.getEquipement().toString()));
    }
    return proximosMantenimientos;
  }
}

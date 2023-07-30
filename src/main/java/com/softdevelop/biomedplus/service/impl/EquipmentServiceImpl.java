package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.NextMaintenanceEquipmentDto;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.MaintenanceEntity;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import com.softdevelop.biomedplus.repository.EquipmentRepository;
import com.softdevelop.biomedplus.repository.MaintenanceRepository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.softdevelop.biomedplus.service.EquipmentService;
import com.softdevelop.biomedplus.service.translator.EquipmentTranslator;
import com.softdevelop.biomedplus.service.translator.SpareTranslator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

  private final EquipmentRepository equipmentRepository;

  private final MaintenanceRepository maintenanceRepository;

  private final EquipmentTranslator equipmentTranslator;

  private final ModelMapper modelMapper;



  @Override
  public Long createEquipment(EquipmentDto equipmentDto) {
    long idEquipment;
    try{
      Boolean exist = equipmentRepository.existsById(equipmentDto.getProvider().getId());
      if (!exist) {
        throw new NotFoundException("Provider not found");
      }

      EquipmentEntity equipmentEntity = new EquipmentEntity();
      EquipmentEntity equipmentSaved = equipmentRepository.save(
              equipmentTranslator.setEquipmentDtoToEquipmentEntity(equipmentEntity, equipmentDto));
      idEquipment = equipmentSaved.getId();
    }catch (RuntimeException e ){
      throw new GenericException(e.getMessage());
    }
    return idEquipment;
  }

  @Override
  public EquipmentDto updateEquipment(Long id, EquipmentDto equipmentRq) {

    Optional<EquipmentEntity> equipmentEntity = equipmentRepository.findById(id);
    if (equipmentEntity.isEmpty()) {
      return null;
    }
    EquipmentEntity equipmentUpdated = equipmentRepository.save(
        equipmentTranslator.setEquipmentDtoToEquipmentEntity(equipmentEntity.get(), equipmentRq));
    return modelMapper.map(equipmentUpdated, EquipmentDto.class);
  }

  @Override
  public List<EquipmentDto> getEquipments() throws GenericException {
    List<EquipmentDto> equipments = new ArrayList<>();

    try {
      List<EquipmentEntity> allEquipments = (List<EquipmentEntity>) equipmentRepository.findAll();
      if (allEquipments == null || allEquipments.isEmpty()) {
        throw new NotFoundException("Equipments not found");
      }
      equipments = modelMapper.map(allEquipments, new TypeToken<List<EquipmentDto>>() {
      }.getType());
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return equipments;
  }

  @Override
  public List<NextMaintenanceEquipmentDto> nextMaintenanceProducts() {
    List<MaintenanceEntity> mantenimientos = maintenanceRepository.
            findByEstimatedDateLessThanEqualAndDoneDateIsNull(LocalDate.now().plusDays(30));
    List<NextMaintenanceEquipmentDto> proximosMantenimientos = new ArrayList<NextMaintenanceEquipmentDto>();
    for (MaintenanceEntity mantenimiento: mantenimientos) {
        proximosMantenimientos.add(new NextMaintenanceEquipmentDto(mantenimiento.getEquipment().getId(),
                mantenimiento.getEquipment().getName(),
                mantenimiento.getEquipment().getBrand(),
                mantenimiento.getEquipment().getModel(),
                mantenimiento.getEquipment().getSeries(),
                mantenimiento.getEquipment().getService(),
                mantenimiento.getEquipment().getArea(),
                mantenimiento.getEquipment().getEquipmentType(),
                mantenimiento.getEquipment().toString()));
    }
    return proximosMantenimientos;
  }
}

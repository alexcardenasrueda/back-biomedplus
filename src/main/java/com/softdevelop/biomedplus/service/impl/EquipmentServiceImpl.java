package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.repository.EquipmentRepository;
import com.softdevelop.biomedplus.repository.ProviderRepository;
import com.softdevelop.biomedplus.service.EquipmentService;
import com.softdevelop.biomedplus.service.translator.EquipmentTranslator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

  private final EquipmentRepository equipmentRepository;

  private final EquipmentTranslator equipmentTranslator;

  private final ModelMapper modelMapper;
  private final ProviderRepository providerRepository;


  @Override
  public EquipmentDto createEquipment(EquipmentDto equipmentDto) {

    EquipmentEntity save;
    try{
      Boolean exist = providerRepository.existsById(equipmentDto.getProvider().getId());
      if (Boolean.FALSE.equals(exist)) {
        throw new NotFoundException("Provider not found");
      }

      EquipmentEntity equipmentEntity = new EquipmentEntity();
      save = equipmentRepository.save(
          equipmentTranslator.setEquipmentDtoToEquipmentEntity(equipmentEntity, equipmentDto));

    }catch (RuntimeException e ){
      throw new GenericException(e.getMessage());
    }
    return modelMapper.map(save, EquipmentDto.class);
  }

  @Override
  public EquipmentDto updateEquipment(Long id, EquipmentDto equipmentRq) {

    Optional<EquipmentEntity> equipmentEntity = equipmentRepository.findById(id);
    if (equipmentEntity.isEmpty()) {
      throw new NotFoundException("Equipment not found");
    }
    EquipmentEntity equipmentUpdated = equipmentRepository.save(
        equipmentTranslator.setEquipmentDtoToEquipmentEntity(equipmentEntity.get(), equipmentRq));
    return modelMapper.map(equipmentUpdated, EquipmentDto.class);
  }

  @Override
  public List<EquipmentDto> getEquipments() throws GenericException {
    List<EquipmentDto> equipments;

    try {
      List<EquipmentEntity> allEquipments = equipmentRepository.findAllByOrderByNameAsc();
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
  public void deleteEquipment(Long id) {
    try {
      equipmentRepository.deleteById(id);
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
  }
}

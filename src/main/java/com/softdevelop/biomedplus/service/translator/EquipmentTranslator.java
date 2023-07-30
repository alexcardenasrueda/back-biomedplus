package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentTranslator {

  private final ProviderTranslator providerTranslator;
  public EquipmentEntity setEquipmentDtoToEquipmentEntity (EquipmentEntity equipmentEntity, EquipmentDto equipmentDto){
      equipmentEntity.setName(equipmentDto.getName().toUpperCase());
      equipmentEntity.setBrand(equipmentDto.getBrand().toUpperCase());
      equipmentEntity.setModel(equipmentDto.getModel().toUpperCase());
      equipmentEntity.setSeries(equipmentDto.getSeries().toUpperCase());
      equipmentEntity.setService(equipmentDto.getService().toUpperCase());
      equipmentEntity.setArea(equipmentDto.getArea().toUpperCase());
      equipmentEntity.setActiveNumber(equipmentDto.getActiveNumber().toUpperCase());
      equipmentEntity.setAccessories(equipmentDto.getAccessories().toUpperCase());
      equipmentEntity.setEquipmentType(equipmentDto.getEquipmentType().toUpperCase());
      ProviderEntity provider = new ProviderEntity();
      provider.setId(equipmentDto.getProvider().getId());
      equipmentEntity.setProvider(provider);
        return equipmentEntity;
  }
}

package com.softdevelop.biomedplus.service.translator;

import static com.softdevelop.biomedplus.util.Constants.EQUIPMENT_IMAGE_DIRECTORY;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import com.softdevelop.biomedplus.util.GenericUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentTranslator {

  private final GenericUtilities genericUtilities;
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

  public EquipmentDto setEquipmentEntityToEquipmentDto(EquipmentEntity entity) {
    // Set image
    String imageToResponse = null;
    if (entity.getImage() != null && !entity.getImage().isEmpty()){
      imageToResponse = genericUtilities.readImageFromServer(EQUIPMENT_IMAGE_DIRECTORY,
          entity.getImage());
    }
   return EquipmentDto.builder()
       .id(entity.getId())
       .name(entity.getName())
       .brand(entity.getBrand())
       .model(entity.getModel())
       .series(entity.getSeries())
       .service(entity.getService())
       .area(entity.getArea())
       .activeNumber(entity.getActiveNumber())
       .accessories(entity.getAccessories())
       .equipmentType(entity.getEquipmentType())
       .image(imageToResponse)
       .build();
  }
}

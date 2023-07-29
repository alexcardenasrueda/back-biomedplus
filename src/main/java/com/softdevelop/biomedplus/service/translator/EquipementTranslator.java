package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.EquipementDto;
import com.softdevelop.biomedplus.model.entity.EquipementEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipementTranslator {

  private final ProviderTranslator providerTranslator;
  public EquipementEntity setEquipoDtoToEquipoEntity (EquipementEntity equipementEntity, EquipementDto equipementDto){
        equipementEntity.setName(equipementDto.getNombre().toUpperCase());
        equipementEntity.setBrand(equipementDto.getMarca().toUpperCase());
        equipementEntity.setModel(equipementDto.getModelo().toUpperCase());
        equipementEntity.setSeries(equipementDto.getSerie().toUpperCase());
        equipementEntity.setService(equipementDto.getServicio().toUpperCase());
        equipementEntity.setArea(equipementDto.getArea().toUpperCase());
        equipementEntity.setActiveNumber(equipementDto.getNumeroActivo().toUpperCase());
        equipementEntity.setAccessories(equipementDto.getAccesorios().toUpperCase());
        equipementEntity.setEquipementType(equipementDto.getTipoEquipo().toUpperCase());
        equipementEntity.setProvider(providerTranslator.proveedorDtoToProveedorEntityT(
            equipementDto.getProveedor()));
        return equipementEntity;
  }
}

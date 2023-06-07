package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipoTranslator {

  private final ProveedorTranslator proveedorTranslator;
  public EquipoEntity setEquipoDtoToEquipoEntity (EquipoEntity equipoEntity, EquipoDto equipoDto){
        equipoEntity.setNombre(equipoDto.getNombre().toUpperCase());
        equipoEntity.setMarca(equipoDto.getMarca().toUpperCase());
        equipoEntity.setModelo(equipoDto.getModelo().toUpperCase());
        equipoEntity.setSerie(equipoDto.getSerie().toUpperCase());
        equipoEntity.setServicio(equipoDto.getServicio().toUpperCase());
        equipoEntity.setArea(equipoDto.getArea().toUpperCase());
        equipoEntity.setNumeroActivo(equipoDto.getNumeroActivo().toUpperCase());
        equipoEntity.setAccesorios(equipoDto.getAccesorios().toUpperCase());
        equipoEntity.setTipoEquipo(equipoDto.getTipoEquipo().toUpperCase());
        equipoEntity.setProveedor(proveedorTranslator.proveedorDtoToProveedorEntityT(equipoDto.getProveedor()));
        return equipoEntity;
  }
}

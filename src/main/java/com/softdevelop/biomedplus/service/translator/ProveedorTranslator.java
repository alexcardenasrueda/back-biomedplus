package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.ProveedorDto;
import com.softdevelop.biomedplus.model.entity.ProveedorEntity;
import org.springframework.stereotype.Component;

@Component
public class ProveedorTranslator {

  public ProveedorEntity proveedorDtoToProveedorEntityT(ProveedorDto proveedorDto) {
    return ProveedorEntity.builder()
        .nombre(proveedorDto.getNombre().toUpperCase())
        .telefono(proveedorDto.getTelefono())
        .ciudad(proveedorDto.getCiudad())
        .direccion(proveedorDto.getDireccion().toUpperCase())
        .build();
  }
}

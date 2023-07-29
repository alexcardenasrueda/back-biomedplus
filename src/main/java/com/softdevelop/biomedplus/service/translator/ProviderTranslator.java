package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import org.springframework.stereotype.Component;

@Component
public class ProviderTranslator {

  public ProviderEntity proveedorDtoToProveedorEntityT(ProviderDto providerDto) {
    return ProviderEntity.builder()
        .name(providerDto.getNombre().toUpperCase())
        .phone(providerDto.getTelefono())
        .city(providerDto.getCiudad())
        .address(providerDto.getDireccion().toUpperCase())
        .build();
  }
}

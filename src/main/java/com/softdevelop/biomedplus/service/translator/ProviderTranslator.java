package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import org.springframework.stereotype.Component;

@Component
public class ProviderTranslator {

  public ProviderEntity providerDtoToProviderEntity(ProviderEntity providerEntity, ProviderDto providerDto) {
    providerEntity.setName(providerDto.getName().toUpperCase());
    providerEntity.setPhone(providerDto.getPhone().toUpperCase());
    providerEntity.setCity(providerDto.getCity().toUpperCase());
    providerEntity.setAddress(providerDto.getAddress().toUpperCase());
    return providerEntity;
  }
}

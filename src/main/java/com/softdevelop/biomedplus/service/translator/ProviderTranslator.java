package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import org.springframework.stereotype.Component;

@Component
public class ProviderTranslator {

  public ProviderEntity providerDtoToProviderEntityT(ProviderDto providerDto) {
    return ProviderEntity.builder()
        .name(providerDto.getName().toUpperCase())
        .phone(providerDto.getTelephone())
        .city(providerDto.getCity())
        .address(providerDto.getAddress().toUpperCase())
        .build();
  }
}

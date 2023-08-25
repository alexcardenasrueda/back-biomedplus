package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpareTranslator {

  public SpareEntity setSpareDtoToSpareEntity (SpareEntity spareEntity, SpareDto spareDto){
      spareEntity.setName(spareDto.getName().toUpperCase());
      spareEntity.setBrand(spareDto.getBrand().toUpperCase());
      spareEntity.setModel(spareDto.getModel().toUpperCase());
      spareEntity.setItem(spareDto.getItem().toUpperCase());
      spareEntity.setCodeReference(spareDto.getCodeReference().toUpperCase());
      spareEntity.setSeries(spareDto.getSeries().toUpperCase());
      spareEntity.setService(spareDto.getService().toUpperCase());
      spareEntity.setQuantity(spareDto.getQuantity());
      spareEntity.setPrice(spareDto.getPrice());
      ProviderEntity provider = new ProviderEntity();
      provider.setId(spareDto.getProvider().getId());
      spareEntity.setProvider(provider);
      return spareEntity;
  }
}

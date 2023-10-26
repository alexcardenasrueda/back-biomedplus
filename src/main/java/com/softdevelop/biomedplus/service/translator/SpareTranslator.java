package com.softdevelop.biomedplus.service.translator;

import static com.softdevelop.biomedplus.util.Constants.EQUIPMENT_IMAGE_DIRECTORY;
import static com.softdevelop.biomedplus.util.Constants.SPARE_IMAGE_DIRECTORY;

import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import com.softdevelop.biomedplus.util.GenericUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpareTranslator {

  private final GenericUtilities genericUtilities;

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

    public SpareDto setSpareEntityToSpareDto(SpareEntity spare) {
      // Set image
      String imageToResponse = null;
      if (spare.getImage() != null && !spare.getImage().isEmpty()){
        imageToResponse = genericUtilities.readImageFromServer(SPARE_IMAGE_DIRECTORY,
            spare.getImage());
      }

      return SpareDto.builder()
          .id(spare.getId())
          .name(spare.getName())
          .brand(spare.getBrand())
          .model(spare.getModel())
          .series(spare.getSeries())
          .service(spare.getService())
          .item(spare.getItem())
          .codeReference(spare.getCodeReference())
          .quantity(spare.getQuantity())
          .price(spare.getPrice())
          .provider(ProviderDto.builder().id(spare.getProvider().getId()).build())
          .image(imageToResponse)
          .build();
    }
}

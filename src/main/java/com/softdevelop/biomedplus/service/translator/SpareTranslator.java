package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpareTranslator {

  private final ProviderTranslator providerTranslator;
  public SpareEntity setRepuestoDtoToRepuestoEntity (SpareEntity repuestoEntity, SpareDto spareDto){
      repuestoEntity.setName(spareDto.getNombre().toUpperCase());
      repuestoEntity.setBrand(spareDto.getMarca().toUpperCase());
      repuestoEntity.setModel(spareDto.getModelo().toUpperCase());
      repuestoEntity.setItem(spareDto.getItem().toUpperCase());
      repuestoEntity.setCodeReference(spareDto.getReferencia().toUpperCase());
      repuestoEntity.setSeries(spareDto.getSerie().toUpperCase());
      repuestoEntity.setService(spareDto.getServicio().toUpperCase());
      repuestoEntity.setQuantity(spareDto.getCantidad());
      repuestoEntity.setPrice(spareDto.getPrecio());
      ProviderEntity proveedor = new ProviderEntity();
      proveedor.setId(spareDto.getProveedorID());
      repuestoEntity.setProvider(proveedor);
      return repuestoEntity;
  }
}

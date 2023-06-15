package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.dto.RepuestoDto;
import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import com.softdevelop.biomedplus.model.entity.ProveedorEntity;
import com.softdevelop.biomedplus.model.entity.RepuestoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepuestoTranslator {

  private final ProveedorTranslator proveedorTranslator;
  public RepuestoEntity setRepuestoDtoToRepuestoEntity (RepuestoEntity repuestoEntity, RepuestoDto repuestoDto){
      repuestoEntity.setNombre(repuestoDto.getNombre().toUpperCase());
      repuestoEntity.setMarca(repuestoDto.getMarca().toUpperCase());
      repuestoEntity.setModelo(repuestoDto.getModelo().toUpperCase());
      repuestoEntity.setItem(repuestoDto.getItem().toUpperCase());
      repuestoEntity.setReferencia(repuestoDto.getReferencia().toUpperCase());
      repuestoEntity.setSerie(repuestoDto.getSerie().toUpperCase());
      repuestoEntity.setServicio(repuestoDto.getServicio().toUpperCase());
      repuestoEntity.setCantidad(repuestoDto.getCantidad());
      repuestoEntity.setPrecio(repuestoDto.getPrecio());
      ProveedorEntity proveedor = new ProveedorEntity();
      proveedor.setId(repuestoDto.getProveedorID());
      repuestoEntity.setProveedor(proveedor);
      return repuestoEntity;
  }
}

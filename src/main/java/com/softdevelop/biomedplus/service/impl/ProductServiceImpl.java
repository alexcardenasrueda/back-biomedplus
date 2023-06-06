package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.model.dto.EquipoRq;
import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import com.softdevelop.biomedplus.model.entity.ProveedorEntity;
import com.softdevelop.biomedplus.repository.ProductRepository;
import com.softdevelop.biomedplus.service.ProductService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;
  private final ModelMapper modelMapper;
  @Override
  public void updateProducts(EquipoRq equipoRq) {
    try {
      Optional<EquipoEntity> equipoEntity =
          repository.findById(equipoRq.getId());
      if (equipoEntity.isEmpty()){
        throw new NotFoundException();
      }
      equipoEntity.get().setNombre(equipoRq.getNombre());
      equipoEntity.get().setMarca(equipoRq.getMarca());
      equipoEntity.get().setModelo(equipoRq.getModelo());
      equipoEntity.get().setSerie(equipoRq.getSerie());
      equipoEntity.get().setServicio(equipoRq.getServicio());
      equipoEntity.get().setArea(equipoRq.getArea());
      equipoEntity.get().setNumeroActivo(equipoRq.getNumeroActivo());
      equipoEntity.get().setAccesorios(equipoRq.getAccesorios());
      equipoEntity.get().setTipoEquipo(equipoRq.getTipoEquipo());
      equipoEntity.get().setProveedor(modelMapper.map(equipoRq.getProveedor(), ProveedorEntity.class));

      repository.save(equipoEntity.get());
    } catch (NotFoundException exception){
      throw new RuntimeException();
    }
  }

}

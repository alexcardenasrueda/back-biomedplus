package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.EquipoDto;

public interface EquipoService {

  EquipoDto updateProducts(Long id, EquipoDto equipoDto);

  EquipoDto getProducts();

}

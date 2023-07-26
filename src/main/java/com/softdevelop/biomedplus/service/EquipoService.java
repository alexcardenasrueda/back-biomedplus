package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.dto.ProximoMantenimientoEquipoDto;

import java.util.List;

public interface EquipoService {

  List<EquipoDto> getProducts() throws GenericException;

  List<ProximoMantenimientoEquipoDto> nextMaintenanceProducts();

  EquipoDto updateProducts(Long id, EquipoDto equipoDto);
}

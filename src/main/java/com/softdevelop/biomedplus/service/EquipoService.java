package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.dto.ProximoMantenimientoEquipoDto;

import java.util.List;

import java.util.List;

public interface EquipoService {

  EquipoDto updateProducts(Long id, EquipoDto equipoDto);

  List<EquipoDto> getProducts() throws GenericException;

  List<ProximoMantenimientoEquipoDto> nextMaintenanceProducts();
}

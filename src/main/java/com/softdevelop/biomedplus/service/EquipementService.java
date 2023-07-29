package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipementDto;
import com.softdevelop.biomedplus.model.dto.NextManteinanceEquipementDto;

import java.util.List;

public interface EquipementService {

  List<EquipementDto> getProducts() throws GenericException;

  List<NextManteinanceEquipementDto> nextMaintenanceProducts();

  EquipementDto updateProducts(Long id, EquipementDto equipementDto);
}

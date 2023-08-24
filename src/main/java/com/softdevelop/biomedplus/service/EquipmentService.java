package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.NextMaintenanceEquipmentDto;
import com.softdevelop.biomedplus.model.dto.SpareDto;

import java.util.List;

public interface EquipmentService {

  EquipmentDto createEquipment(EquipmentDto equipmentDto);

  EquipmentDto updateEquipment(Long id, EquipmentDto equipmentDto);

  List<EquipmentDto> getEquipments() throws GenericException;

  void deleteEquipment(Long id);
}

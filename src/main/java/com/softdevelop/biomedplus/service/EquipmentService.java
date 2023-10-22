package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface EquipmentService {

  EquipmentDto createEquipment(EquipmentDto equipmentDto, MultipartFile image);

  EquipmentDto updateEquipment(Long id, EquipmentDto equipmentDto);

  List<EquipmentDto> getEquipments() throws GenericException;

  void deleteEquipment(Long id);
}

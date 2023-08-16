package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.NextMaintenanceEquipmentDto;

import java.util.List;

public interface MaintenanceService {

  List<NextMaintenanceEquipmentDto> nextExpected();

}

package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.MaintenanceDto;
import com.softdevelop.biomedplus.model.dto.StatusDto;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.MaintenanceEntity;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import com.softdevelop.biomedplus.model.entity.StatusEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceTranslator {

  public MaintenanceEntity setMaintenanceDtoToMaintenanceEntity (MaintenanceEntity maintenanceEntity, MaintenanceDto maintenanceDto){
      maintenanceEntity.setEstimatedDate(maintenanceDto.getEstimatedDate());
      maintenanceEntity.setDoneDate(maintenanceDto.getDoneDate());
      EquipmentEntity equipment = new EquipmentEntity();
      equipment.setId(maintenanceDto.getEquipment().getId());
      maintenanceEntity.setEquipment(equipment);
      StatusEntity status = new StatusEntity();
      status.setId(maintenanceDto.getStatus().getId());
      maintenanceEntity.setStatus(status);
      return maintenanceEntity;
  }
}

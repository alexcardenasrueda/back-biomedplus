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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.softdevelop.biomedplus.util.Constants.FORMAT_DATE;

@Component
@RequiredArgsConstructor
public class MaintenanceTranslator {

  public MaintenanceEntity setMaintenanceDtoToMaintenanceEntity (MaintenanceEntity maintenanceEntity, MaintenanceDto maintenanceDto){
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
      maintenanceEntity.setEstimatedDate(LocalDate.parse(maintenanceDto.getEstimatedDate(), formatter));
      if (maintenanceDto.getDoneDate() != null) {
          maintenanceEntity.setDoneDate(LocalDate.parse(maintenanceDto.getDoneDate(), formatter));
      }
      EquipmentEntity equipment = new EquipmentEntity();
      equipment.setId(maintenanceDto.getEquipment().getId());
      maintenanceEntity.setEquipment(equipment);
      StatusEntity status = new StatusEntity();
      status.setId(maintenanceDto.getStatus().getId());
      maintenanceEntity.setStatus(status);
      return maintenanceEntity;
  }
}

package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.enums.Status;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.NextMaintenanceEquipmentDto;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.MaintenanceEntity;
import com.softdevelop.biomedplus.repository.EquipmentRepository;
import com.softdevelop.biomedplus.repository.MaintenanceRepository;
import com.softdevelop.biomedplus.service.EquipmentService;
import com.softdevelop.biomedplus.service.MaintenanceService;
import com.softdevelop.biomedplus.service.translator.EquipmentTranslator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

  private final MaintenanceRepository maintenanceRepository;

  @Override
  public List<NextMaintenanceEquipmentDto> nextExpected() {
    List<MaintenanceEntity> maintenances = maintenanceRepository.
            findByEstimatedDateLessThanEqualAndStatusNameAndDoneDateIsNull(LocalDate.now().plusDays(30),
                    Status.CREATED.name());
    List<NextMaintenanceEquipmentDto> nextMaintenance = new ArrayList<NextMaintenanceEquipmentDto>();

    for (MaintenanceEntity maintenance: maintenances) {
      nextMaintenance.add(new NextMaintenanceEquipmentDto(
              maintenance.getEquipment().getId(),
              maintenance.getEquipment().getName(),
              maintenance.getEquipment().getBrand(),
              maintenance.getEquipment().getModel(),
              maintenance.getEquipment().getSeries(),
              maintenance.getEquipment().getService(),
              maintenance.getEquipment().getArea(),
              maintenance.getEquipment().getEquipmentType(),
              maintenance.getEstimatedDate().toString()));
    }
    return nextMaintenance;
  }
}

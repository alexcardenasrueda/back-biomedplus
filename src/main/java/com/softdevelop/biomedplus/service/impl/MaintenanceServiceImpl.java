package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.enums.Status;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.*;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.MaintenanceEntity;
import com.softdevelop.biomedplus.model.entity.TicketEntity;
import com.softdevelop.biomedplus.repository.EquipmentRepository;
import com.softdevelop.biomedplus.repository.MaintenanceRepository;
import com.softdevelop.biomedplus.repository.StatusRepository;
import com.softdevelop.biomedplus.service.EquipmentService;
import com.softdevelop.biomedplus.service.MaintenanceService;
import com.softdevelop.biomedplus.service.translator.EquipmentTranslator;
import com.softdevelop.biomedplus.service.translator.MaintenanceTranslator;
import com.softdevelop.biomedplus.service.translator.TicketTranslator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

  private final MaintenanceRepository maintenanceRepository;

  private final EquipmentRepository equipmentRepository;

  private final StatusRepository statusRepository;

  private final MaintenanceTranslator maintenanceTranslator;

  private final ModelMapper modelMapper;



  @Override
  public List<NextMaintenanceEquipmentDto> nextExpected() {
    List<MaintenanceEntity> maintenances = maintenanceRepository.
            findByEstimatedDateLessThanEqualAndStatusNameAndDoneDateIsNull(
                    LocalDate.now().plusDays(30),
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

  @Override
  public Long createMaintenance(MaintenanceDto maintenanceDto) {
    long idMaintenance;
    try{
      Boolean exist = equipmentRepository.existsById(maintenanceDto.getEquipment().getId());
      if (!exist) {
        throw new NotFoundException("Equipment not found");
      }

      MaintenanceEntity maintenanceEntity = new MaintenanceEntity();
      maintenanceDto.setStatus(new StatusDto());
      maintenanceDto.getStatus().setId(1L);
      MaintenanceEntity maintenanceSaved = maintenanceRepository.save(
              maintenanceTranslator.setMaintenanceDtoToMaintenanceEntity(maintenanceEntity, maintenanceDto));
      idMaintenance = maintenanceSaved.getId();
    }catch (RuntimeException e ){
      throw new GenericException(e.getMessage());
    }
    return idMaintenance;
  }

  @Override
  public MaintenanceDto updateMaintenance(Long id, MaintenanceDto maintenanceDto) {
    MaintenanceDto maintenanceSavedDto;
    try{
      boolean exist = maintenanceRepository.existsById(id);
      if (!exist) {
        throw new NotFoundException("Maintenance not found");
      }

      exist = equipmentRepository.existsById(maintenanceDto.getEquipment().getId());
      if (!exist) {
        throw new NotFoundException("Equipment not found");
      }

      if (maintenanceDto.getStatus() == null) {
            maintenanceDto.setStatus(new StatusDto());
            maintenanceDto.getStatus().setId(1L);
      }else {
        exist = statusRepository.existsById(maintenanceDto.getStatus().getId());
        if (!exist) {
          throw new NotFoundException("Status not found");
        }
      }

      MaintenanceEntity maintenanceEntity = new MaintenanceEntity();
      maintenanceEntity.setId(id);


      MaintenanceEntity maintenanceSaved = maintenanceRepository.save(
              maintenanceTranslator.setMaintenanceDtoToMaintenanceEntity(maintenanceEntity, maintenanceDto));
      maintenanceSavedDto = modelMapper.map(maintenanceSaved, MaintenanceDto.class);
    }catch (RuntimeException e ){
      throw new GenericException(e.getMessage());
    }
    return maintenanceSavedDto;  }
}

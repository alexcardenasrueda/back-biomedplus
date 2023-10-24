package com.softdevelop.biomedplus.service.impl;

import static com.softdevelop.biomedplus.util.Constants.EQUIPMENT_IMAGE_DIRECTORY;

import com.softdevelop.biomedplus.enums.Status;
import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.MaintenanceDto;
import com.softdevelop.biomedplus.model.dto.StatusDto;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.MaintenanceEntity;
import com.softdevelop.biomedplus.repository.EquipmentRepository;
import com.softdevelop.biomedplus.repository.ProviderRepository;
import com.softdevelop.biomedplus.service.EquipmentService;
import com.softdevelop.biomedplus.service.translator.EquipmentTranslator;
import com.softdevelop.biomedplus.util.GenericUtilities;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

  private final EquipmentRepository equipmentRepository;
  private final EquipmentTranslator equipmentTranslator;
  private final ModelMapper modelMapper;
  private final ProviderRepository providerRepository;
  private final GenericUtilities genericUtilities;


  @Override
  public EquipmentDto createEquipment(EquipmentDto equipmentRq, MultipartFile image) {
    EquipmentEntity saved;
    try{
      Boolean exist = providerRepository.existsById(equipmentRq.getProvider().getId());

      if (Boolean.FALSE.equals(exist)) {
        throw new NotFoundException("Provider not found");
      }
      EquipmentEntity equipmentEntityToSave = new EquipmentEntity();
      if (image != null && !image.isEmpty()){
        genericUtilities.imageBuilder(image, EQUIPMENT_IMAGE_DIRECTORY);
        equipmentEntityToSave.setImage(image.getOriginalFilename());
      }
      saved = equipmentRepository.save(
          equipmentTranslator.setEquipmentDtoToEquipmentEntity(equipmentEntityToSave, equipmentRq));
    } catch (RuntimeException e ){
      throw new GenericException(e.getMessage());
    }
    return modelMapper.map(saved, EquipmentDto.class);
  }


  @Override
  public EquipmentDto updateEquipment(Long id, EquipmentDto equipmentRq, MultipartFile image) {

    Optional<EquipmentEntity> equipmentEntityToUpdate = equipmentRepository.findById(id);
    if (equipmentEntityToUpdate.isEmpty()) {
      throw new NotFoundException("Equipment not found");
    }
    if (image != null && !image.isEmpty()){
      genericUtilities.imageBuilder(image, EQUIPMENT_IMAGE_DIRECTORY);
      equipmentEntityToUpdate.get().setImage(image.getOriginalFilename());
    }
    EquipmentEntity equipmentUpdated = equipmentRepository.save(
        equipmentTranslator.setEquipmentDtoToEquipmentEntity(equipmentEntityToUpdate.get(), equipmentRq));
    return modelMapper.map(equipmentUpdated, EquipmentDto.class);
  }

  @Override
  public List<EquipmentDto> getEquipments() throws GenericException {

    LoggerEvent.info()
        .forClass(EquipmentServiceImpl.class)
        .withField("Action: ", "getEquipments-init")
        .log();

    List<EquipmentDto> equipments = new ArrayList<>();
    try {
      List<EquipmentEntity> allEquipments = equipmentRepository.findAllByOrderByNameAsc();
      if (allEquipments == null || allEquipments.isEmpty()) {
        throw new NotFoundException("Equipments not found");
      }

      for(EquipmentEntity equipment :allEquipments) {
        EquipmentDto equipmentDto = equipmentTranslator.setEquipmentEntityToEquipmentDto(
            equipment);
        for(MaintenanceEntity maintenance : equipment.getMaintenances()) {
          if (maintenance.getDoneDate() == null ) {
            MaintenanceDto maintenanceDto = new MaintenanceDto();
            maintenanceDto.setId(maintenance.getId());
            maintenanceDto.setEstimatedDate(maintenance.getEstimatedDate().toString());
            maintenanceDto.setStatus(new StatusDto(maintenance.getStatus().getId(),
                   Status.GetStatusByName(maintenance.getStatus().getName())));
            equipmentDto.setNextMaintenance(maintenanceDto);
            break;
          }
        }
        equipments.add(equipmentDto);
      }
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return equipments;
  }

  @Override
  public void deleteEquipment(Long id) {
    try {
      equipmentRepository.deleteById(id);
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
  }
}

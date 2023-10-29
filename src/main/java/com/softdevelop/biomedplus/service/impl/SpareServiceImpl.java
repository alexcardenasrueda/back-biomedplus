package com.softdevelop.biomedplus.service.impl;

import static com.softdevelop.biomedplus.util.Constants.EQUIPMENT_IMAGE_DIRECTORY;
import static com.softdevelop.biomedplus.util.Constants.SPARE_IMAGE_DIRECTORY;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.entity.EquipmentEntity;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import com.softdevelop.biomedplus.repository.ProviderRepository;
import com.softdevelop.biomedplus.repository.SpareRepository;
import com.softdevelop.biomedplus.service.SpareService;
import com.softdevelop.biomedplus.service.translator.SpareTranslator;
import com.softdevelop.biomedplus.util.GenericUtilities;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SpareServiceImpl implements SpareService {

    private final SpareRepository spareRepository;
    private final ProviderRepository providerRepository;
    private final ModelMapper modelMapper;
    private final SpareTranslator spareTranslator;
    private final GenericUtilities genericUtilities;


    @Override
    public SpareDto createSpare(SpareDto spareDto, MultipartFile image) {
        SpareEntity spareEntitySaved;
        try{
            boolean exist = providerRepository.existsById(spareDto.getProvider().getId());
            if (!exist) {
                throw new NotFoundException("Provider not found");
            }
            SpareEntity spareEntity = new SpareEntity();
            if (image != null && !image.isEmpty()){
                genericUtilities.imageBuilder(image, SPARE_IMAGE_DIRECTORY);
                spareEntity.setImage(image.getOriginalFilename());
            }
            spareEntitySaved = spareRepository.save(
                spareTranslator.setSpareDtoToSpareEntity(spareEntity, spareDto));
            return modelMapper.map(spareEntitySaved, SpareDto.class);
        } catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public SpareDto updateSpare(Long id, SpareDto spareDto, MultipartFile image) {
        SpareDto spareSavedDto;
        try{
            Optional<SpareEntity> spareEntityToUpdate = spareRepository.findById(id);
            if (spareEntityToUpdate.isEmpty()) {
                throw new NotFoundException("Spare not found");
            }

            boolean exist = providerRepository.existsById(spareDto.getProvider().getId());
            if (!exist) {
                throw new NotFoundException("Provider not found");
            }

            if (image != null && !image.isEmpty()){
                genericUtilities.imageBuilder(image, SPARE_IMAGE_DIRECTORY);
                spareEntityToUpdate.get().setImage(image.getOriginalFilename());
            } else {
                spareEntityToUpdate.get().setImage(spareEntityToUpdate.get().getImage());
            }

            spareEntityToUpdate.get().setId(id);
            SpareEntity spareSaved = spareRepository.save(
                    spareTranslator.setSpareDtoToSpareEntity(spareEntityToUpdate.get(), spareDto));
            spareSavedDto = modelMapper.map(spareSaved, SpareDto.class);
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return spareSavedDto;
    }

    @Override
    public List<SpareDto> getSpares() {

        LoggerEvent.info()
            .forClass(SpareServiceImpl.class)
            .withField("Action: ", "getSpares-init")
            .log();

        List<SpareDto> spares = new ArrayList<>();

        try {
            List<SpareEntity> allSpares = spareRepository.findAllByOrderByNameAsc();
            if (allSpares == null || allSpares.isEmpty()) {
                throw new NotFoundException("Spares not found");
            }

            for (SpareEntity spareItem : allSpares) {
                SpareDto spareTemp = spareTranslator.setSpareEntityToSpareDto(spareItem);
                spares.add(spareTemp);
            }
            return spares;
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public void deleteSpare(Long id) {
        try {
            spareRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
    }
}

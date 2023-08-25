package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import com.softdevelop.biomedplus.repository.ProviderRepository;
import com.softdevelop.biomedplus.repository.SpareRepository;
import com.softdevelop.biomedplus.service.SpareService;
import com.softdevelop.biomedplus.service.translator.SpareTranslator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpareServiceImpl implements SpareService {

    private final SpareRepository spareRepository;

    private final ProviderRepository providerRepository;

    private final ModelMapper modelMapper;

    private final SpareTranslator spareTranslator;


    @Override
    public SpareDto createSpare(SpareDto spareDto) {

        try{

            boolean exist = providerRepository.existsById(spareDto.getProvider().getId());
            if (!exist) {
                throw new NotFoundException("Provider not found");
            }
            SpareEntity spareEntity = new SpareEntity();
            SpareEntity spareEntitySaved = spareRepository.save(
                spareTranslator.setSpareDtoToSpareEntity(spareEntity, spareDto));
            return modelMapper.map(spareEntitySaved, SpareDto.class);
        } catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    public SpareDto updateSpare(Long id, SpareDto spareDto) {
        SpareDto spareSavedDto;
        try{
            boolean exist = spareRepository.existsById(id);
            if (!exist) {
                throw new NotFoundException("Spare not found");
            }

            exist = providerRepository.existsById(spareDto.getProvider().getId());
            if (!exist) {
                throw new NotFoundException("Provider not found");
            }

            SpareEntity spareEntity = new SpareEntity();
            spareEntity.setId(id);
            SpareEntity spareSaved = spareRepository.save(
                    spareTranslator.setSpareDtoToSpareEntity(spareEntity, spareDto));
            spareSavedDto = modelMapper.map(spareSaved, SpareDto.class);
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return spareSavedDto;
    }

    @Override
    public List<SpareDto> getSpares() {
        List<SpareDto> spares;

        try {
            List<SpareEntity> allSpares = spareRepository.findAllByOrderByNameAsc();
            if (allSpares == null || allSpares.isEmpty()) {
                throw new NotFoundException("Spares not found");
            }
            spares = modelMapper.map(allSpares, new TypeToken<List<SpareDto>>() {
            }.getType());
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return spares;
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

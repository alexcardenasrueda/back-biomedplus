package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.SpareDto;
import com.softdevelop.biomedplus.model.entity.SpareEntity;
import com.softdevelop.biomedplus.repository.ProviderRepository;
import com.softdevelop.biomedplus.repository.SpareRepository;
import com.softdevelop.biomedplus.service.SpareService;
import com.softdevelop.biomedplus.service.translator.SpareTranslator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpareServiceImpl implements SpareService {

    private final SpareRepository spareRepository;

    private final ProviderRepository providerRepository;

    private final ModelMapper modelMapper;

    private final SpareTranslator spareTranslator;


    @Override
    public Long createReplacement(SpareDto spareDto) {
        long idRepuesto;
        try{
            Boolean exist = providerRepository.existsById(spareDto.getProveedorID());
            if (!exist) {
                throw new NotFoundException("Proveedor not found");
            }

            SpareEntity spareEntity = new SpareEntity();
            SpareEntity repuestoSaved = spareRepository.save(
                    spareTranslator.setRepuestoDtoToRepuestoEntity(spareEntity, spareDto));
            idRepuesto = repuestoSaved.getId();
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return idRepuesto;
    }

    @Override
    public SpareDto updateReplacement(Long id, SpareDto spareDto) {
        SpareDto repuestoSavedDto;
        try{
            Boolean exist = spareRepository.existsById(id);
            if (!exist) {
                throw new NotFoundException("Repuesto not found");
            }

            exist = providerRepository.existsById(spareDto.getProveedorID());
            if (!exist) {
                throw new NotFoundException("Proveedor not found");
            }

            SpareEntity spareEntity = new SpareEntity();
            spareEntity.setId(id);
            SpareEntity repuestoSaved = spareRepository.save(
                    spareTranslator.setRepuestoDtoToRepuestoEntity(spareEntity, spareDto));
            repuestoSavedDto = modelMapper.map(repuestoSaved, SpareDto.class);
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return repuestoSavedDto;
    }
}

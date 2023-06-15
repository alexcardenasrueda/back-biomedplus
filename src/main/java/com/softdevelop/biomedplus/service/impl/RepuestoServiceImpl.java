package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.ProveedorNotFoundException;
import com.softdevelop.biomedplus.exception.RepuestoNotFoundException;
import com.softdevelop.biomedplus.model.dto.RepuestoDto;
import com.softdevelop.biomedplus.model.entity.RepuestoEntity;
import com.softdevelop.biomedplus.repository.ProveedorRepository;
import com.softdevelop.biomedplus.repository.RepuestoRepository;
import com.softdevelop.biomedplus.service.RepuestoService;
import com.softdevelop.biomedplus.service.translator.RepuestoTranslator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepuestoServiceImpl implements RepuestoService {

    private final RepuestoRepository repuestoRepository;

    private final ProveedorRepository proveedorRepository;

    private final ModelMapper modelMapper;

    private final RepuestoTranslator repuestoTranslator;


    @Override
    public Long createReplacement(RepuestoDto repuestoDto) throws ProveedorNotFoundException, GenericException {
        long idRepuesto;
        try{
            Boolean exist = proveedorRepository.existsById(repuestoDto.getProveedorID());
            if (!exist) {
                throw new ProveedorNotFoundException("Proveedor not found");
            }

            RepuestoEntity repuestoEntity = new RepuestoEntity();
            RepuestoEntity repuestoSaved = repuestoRepository.save(
                    repuestoTranslator.setRepuestoDtoToRepuestoEntity(repuestoEntity, repuestoDto));
            idRepuesto = repuestoSaved.getId();
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return idRepuesto;
    }

    @Override
    public RepuestoDto updateReplacement(Long id, RepuestoDto repuestoDto) throws GenericException, ProveedorNotFoundException {
        RepuestoDto repuestoSavedDto;
        try{
            Boolean exist = repuestoRepository.existsById(id);
            if (!exist) {
                throw new RepuestoNotFoundException("Repuesto not found");
            }

            exist = proveedorRepository.existsById(repuestoDto.getProveedorID());
            if (!exist) {
                throw new ProveedorNotFoundException("Proveedor not found");
            }

            RepuestoEntity repuestoEntity = new RepuestoEntity();
            repuestoEntity.setId(id);
            RepuestoEntity repuestoSaved = repuestoRepository.save(
                    repuestoTranslator.setRepuestoDtoToRepuestoEntity(repuestoEntity, repuestoDto));
            repuestoSavedDto = modelMapper.map(repuestoSaved, RepuestoDto.class);
        }catch (RuntimeException e ){
            throw new GenericException(e.getMessage());
        }
        return repuestoSavedDto;
    }
}

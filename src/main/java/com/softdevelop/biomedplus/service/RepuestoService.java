package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.ProveedorNotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipoDto;
import com.softdevelop.biomedplus.model.dto.RepuestoDto;

public interface RepuestoService {

    Long createReplacement(RepuestoDto equipoDto) throws ProveedorNotFoundException, GenericException;

    RepuestoDto updateReplacement(Long id,RepuestoDto equipoDto) throws GenericException, ProveedorNotFoundException;

}

package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.RepuestoDto;

public interface RepuestoService {

    Long createReplacement(RepuestoDto equipoDto);

    RepuestoDto updateReplacement(Long id,RepuestoDto equipoDto);

}

package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.SpareDto;

public interface SpareService {

    Long createReplacement(SpareDto equipoDto);

    SpareDto updateReplacement(Long id, SpareDto equipoDto);

}

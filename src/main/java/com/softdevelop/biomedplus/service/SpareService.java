package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.SpareDto;

import java.util.List;

public interface SpareService {

    Long createSpare(SpareDto equipoDto);

    SpareDto updateSpare(Long id, SpareDto equipoDto);

    List<SpareDto> getSpares();

}

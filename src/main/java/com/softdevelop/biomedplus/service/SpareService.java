package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.SpareDto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SpareService {

    SpareDto createSpare(SpareDto equipoDto, MultipartFile image);

    SpareDto updateSpare(Long id, SpareDto equipoDto, MultipartFile image);

    List<SpareDto> getSpares();

    void deleteSpare(Long id);
}

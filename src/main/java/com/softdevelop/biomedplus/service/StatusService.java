package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.StatusDto;

public interface StatusService {

  StatusDto getStatusById(Long id);
}

package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.model.dto.SolicitudDto;

import java.util.List;

public interface SolicitudService {
    List<SolicitudDto> getSolicitudes() throws GenericException;
}

package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.StatusDto;
import com.softdevelop.biomedplus.model.entity.StatusEntity;
import com.softdevelop.biomedplus.repository.StatusRepository;
import com.softdevelop.biomedplus.service.StatusService;
import com.softdevelop.biomedplus.service.translator.StatusTranslator;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final StatusTranslator statusTranslator;

    @Override
    public StatusDto getStatusById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        LoggerEvent.info()
            .forClass(StatusServiceImpl.class)
            .withField("Action: ", "getStatusById-init")
            .log();

        try {
            Optional<StatusEntity> statusEntity = statusRepository.findById(id);
            if (statusEntity.isEmpty()){
                throw new NotFoundException("Status not found");
            }
            return statusTranslator.statusEntityToStatusDto(statusEntity.get());
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }
}

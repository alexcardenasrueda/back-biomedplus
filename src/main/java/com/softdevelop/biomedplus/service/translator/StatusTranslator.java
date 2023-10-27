package com.softdevelop.biomedplus.service.translator;

import com.softdevelop.biomedplus.enums.Status;
import com.softdevelop.biomedplus.model.dto.StatusDto;
import com.softdevelop.biomedplus.model.entity.StatusEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusTranslator {

  public StatusDto statusEntityToStatusDto(StatusEntity statusEntity) {
    return StatusDto.builder()
        .id(statusEntity.getId())
        .name(Status.GetStatusByName(statusEntity.getName()))
        .build();
  }
}

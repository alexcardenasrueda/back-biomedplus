package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import com.softdevelop.biomedplus.repository.ProviderRepository;
import com.softdevelop.biomedplus.service.ProviderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

  private final ProviderRepository providerRepository;

  private final ModelMapper modelMapper;


  @Override
  public List<ProviderDto> getProviders() {
    List<ProviderDto> providers;

    try {
      List<ProviderEntity> allProviders = (List<ProviderEntity>) providerRepository.findAll();
      if (allProviders.isEmpty()) {
        throw new NotFoundException("Providers not found");
      }
      providers = modelMapper.map(allProviders, new TypeToken<List<EquipmentDto>>() {
      }.getType());
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return providers;
  }
}

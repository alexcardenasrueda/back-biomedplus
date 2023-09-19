package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import com.softdevelop.biomedplus.repository.ProviderRepository;
import com.softdevelop.biomedplus.service.ProviderService;
import com.softdevelop.biomedplus.service.translator.ProviderTranslator;
import com.softdevelop.biomedplus.util.logs.LoggerEvent;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

  private final ProviderRepository providerRepository;
  private final ProviderTranslator providerTranslator;

  private final ModelMapper modelMapper;


  @Override
  public List<ProviderDto> getProviders() {
    List<ProviderDto> providers;

    try {
      List<ProviderEntity> allProviders = providerRepository.findAllByOrderByNameAsc();
      if (allProviders.isEmpty()) {
        throw new NotFoundException("Providers not found");
      }
      providers = modelMapper.map(allProviders, new TypeToken<List<ProviderDto>>() {
      }.getType());
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
    return providers;
  }

  @Override
  public ProviderDto createProvider(ProviderDto providerRq) {
    LoggerEvent.info()
        .forClass(ProviderServiceImpl.class)
        .withField("Action: ", "createProvider")
        .withField("ProviderRequest", providerRq)
        .log();
    ProviderEntity save;
    try{
      ProviderEntity providerEntity =  new ProviderEntity();
      save = providerRepository.save(
          providerTranslator.providerDtoToProviderEntity(providerEntity, providerRq));
    }catch (RuntimeException e ){
      throw new GenericException(e.getMessage());
    }
    return modelMapper.map(save, ProviderDto.class);
  }

  @Override
  public ProviderDto updateProvider(Long id, ProviderDto providerRq) {
    try {
      Optional<ProviderEntity> providerEntity = providerRepository.findById(id);
      if (providerEntity.isEmpty()) {
        throw new NotFoundException("Provider not found");
      }
      ProviderEntity providerUpdated = providerRepository.save(
          providerTranslator.providerDtoToProviderEntity(providerEntity.get(), providerRq));
      return modelMapper.map(providerUpdated, ProviderDto.class);
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
  }

  @Override
  public void deleteProvider(Long id) {
    try {
      providerRepository.deleteById(id);
    } catch (RuntimeException e) {
      throw new GenericException(e.getMessage());
    }
  }
}

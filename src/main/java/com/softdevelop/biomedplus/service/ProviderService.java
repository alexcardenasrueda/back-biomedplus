package com.softdevelop.biomedplus.service;

import com.softdevelop.biomedplus.model.dto.ProviderDto;
import java.util.List;

public interface ProviderService {

  List<ProviderDto> getProviders();

  ProviderDto createProvider(ProviderDto providerRq);

  void deleteProvider(Long id);

  ProviderDto updateProvider(Long id, ProviderDto providerRq);
}

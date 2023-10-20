package com.softdevelop.biomedplus.service.impl;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import com.softdevelop.biomedplus.repository.ProviderRepository;
import com.softdevelop.biomedplus.service.translator.ProviderTranslator;
import com.softdevelop.biomedplus.util.JsonReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)

class ProviderServiceImplTest {

  ProviderServiceImpl service;

  @Mock private ProviderRepository repository;
  @Mock private ProviderTranslator translator;
  @Mock private ModelMapper modelMapper;

  @BeforeEach
      void init(){
    service = new ProviderServiceImpl(repository, translator, modelMapper);
  }

  @Test
  void getProviders() throws IOException {
    assertTrue(true);
  }


}
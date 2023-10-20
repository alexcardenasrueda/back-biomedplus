package com.softdevelop.biomedplus.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.ProviderDto;
import com.softdevelop.biomedplus.service.MaintenanceService;
import com.softdevelop.biomedplus.service.ProviderService;
import com.softdevelop.biomedplus.util.JsonReader;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)

class ProviderControllerTest {

  @InjectMocks
  private ProviderController controller;
  MockMvc mockMvc;
  @Mock
  private ProviderService service;
  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new ProviderController(service)).build();
  }

  @Test
  void getProviders() throws IOException {

    List expectedRs =
        JsonReader.readObject("json/controller/get_providers_rs.json", List.class);

    Mockito.when(service.getProviders()).thenReturn(expectedRs);

    ResponseEntity<List<ProviderDto>> response = controller.getProviders();

    assertNotNull(response.getBody());
    assertEquals(48, response.getBody().size());
  }
}
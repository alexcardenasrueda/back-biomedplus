package com.softdevelop.biomedplus.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.softdevelop.biomedplus.model.dto.MaintenanceDto;
import com.softdevelop.biomedplus.service.MaintenanceService;
import com.softdevelop.biomedplus.util.JsonReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class MaintenanceControllerTest {

  @InjectMocks
  private MaintenanceController controller;
  MockMvc mockMvc;
  @Mock
  private MaintenanceService service;
  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new MaintenanceController(service)).build();
  }

  @Test
  void createMaintenance() throws IOException, URISyntaxException {

    MaintenanceDto rq =
        JsonReader.readObject("json/controller/create_maintenance_rq.json", MaintenanceDto.class);

    Mockito.when(service.createMaintenance(any(MaintenanceDto.class)))
        .thenReturn(Long.valueOf(1));

    ResponseEntity<Long> response = controller.createMaintenance(rq);

    assertNotNull(response);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(new URI("maintenances/1"), response.getHeaders().getLocation());

  }
}
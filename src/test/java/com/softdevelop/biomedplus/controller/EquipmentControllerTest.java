package com.softdevelop.biomedplus.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.service.EquipmentService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class EquipmentControllerTest {

  @InjectMocks
  private EquipmentController controller;
  MockMvc mockMvc;

  @Mock
  private EquipmentService service;
  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new EquipmentController(service)).build();
  }

  @Test
  void getEquipments() throws IOException {

    List expectedRs =
        JsonReader.readObject("json/controller/get_equipments_rs.json", List.class);

    Mockito.when(service.getEquipments()).thenReturn(expectedRs);

    ResponseEntity<List<EquipmentDto>> response = controller.getEquipments();

    assertNotNull(response.getBody());
    assertEquals(6, response.getBody().size());
  }

  @Test
  void createEquipment() throws IOException {
    EquipmentDto rq =
        JsonReader.readObject("json/controller/create_equipment_rq.json", EquipmentDto.class);
    assertTrue(true);

    EquipmentDto expectedRs =
        JsonReader.readObject("json/controller/create_equipment_rs.json", EquipmentDto.class);

    Mockito.when(service.createEquipment(any(EquipmentDto.class)))
        .thenReturn(expectedRs);

    ResponseEntity<EquipmentDto> response = controller.createEquipment(rq);

    assertNotNull(response);
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedRs, response.getBody());
  }
}
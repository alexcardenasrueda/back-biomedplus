package com.softdevelop.biomedplus.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.service.UserService;
import com.softdevelop.biomedplus.util.JsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {


  @InjectMocks private UserController controller;
  MockMvc mockMvc;

  @Mock
  private UserService service;

  private static final ObjectMapper MAPPER =
      new ObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new UserController(service)).build();
  }

  @Test
  @DisplayName("Test that allow to create an user")
  void createUser() throws IOException {
    UserDto rq =
        JsonReader.readObject("json/controller/user_rq.json", UserDto.class);
    assertTrue(true);

    UserDto expectedRs =
        JsonReader.readObject("json/controller/user_rs.json", UserDto.class);

    Mockito.when(service.createUser(any(UserDto.class)))
        .thenReturn(expectedRs);

    ResponseEntity<UserDto> response = controller.createUser(rq);

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedRs, response.getBody());
  }

  @Test
  void getUserByEmail() {
    assertTrue(true);
  }
}

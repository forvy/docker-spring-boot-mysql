package com.forvy.spring.datajpa.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Sql({ "/carparks.sql", "/carpark_info.sql" })
@AutoConfigureMockMvc
public class CarParkControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Test with latitude longitude params")
  public void testGetNearestCarParksEndpoint() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/carparks/nearest")
        .param("latitude", "1.5")
        .param("longitude", "1.5")
        .param("page", "1")
        .param("per_page", "10"))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].address").value("Address 1"));
  }

  @Test
  @DisplayName("Test with latitude param")
  public void testGetNearestCarParksEndpointLatitudeParam() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/carparks/nearest")
        .param("latitude", "1.5")
        .param("page", "1")
        .param("per_page", "10"))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  @DisplayName("Test with longitude param")
  public void testGetNearestCarParksEndpointLongitudeParam() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/carparks/nearest")
        .param("longitude", "1.5")
        .param("page", "1")
        .param("per_page", "10"))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }
}

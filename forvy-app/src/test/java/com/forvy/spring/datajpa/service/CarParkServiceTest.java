package com.forvy.spring.datajpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.forvy.spring.datajpa.dto.CarParkDto;
import com.forvy.spring.datajpa.model.CarPark;
import com.forvy.spring.datajpa.model.CarparkInfo;
import com.forvy.spring.datajpa.repository.CarParkRepository;
import com.forvy.spring.datajpa.repository.CarparkInfoRepository;

@SpringBootTest(properties = "spring.config.name=application-test")
@Sql({ "/carparks.sql", "/carpark_info.sql" })
public class CarParkServiceTest {

  @InjectMocks
  private CarParkService carParkService;

  @Mock
  private CarParkRepository carParkRepository;

  @Mock
  private CarparkInfoRepository carparkInfoRepository;

  @Test
  @DisplayName("Test nearest car park with available lot")
  public void testGetNearestCarParks_WithAvailableLots() {
    double latitude = 1.5;
    double longitude = 1.5;
    CarPark carPark = new CarPark("CP001", "Address 1", 1.0, 1.0);
    CarparkInfo carparkInfo = new CarparkInfo("CP001", 100, 50, OffsetDateTime.now());
    when(carParkRepository.findByLocation(latitude, longitude)).thenReturn(Collections.singletonList(carPark));
    when(carparkInfoRepository.findByCarParkNumber("CP001")).thenReturn(carparkInfo);

    List<CarParkDto> carParkDtos = carParkService.getNearestCarParks(latitude, longitude, 1, 10);

    assertThat(carParkDtos).hasSize(1);
    assertThat(carParkDtos.get(0).getAddress()).isEqualTo("Address 1");
    assertThat(carParkDtos.get(0).getAvailableLots()).isEqualTo(50);
  }

  @Test
  @DisplayName("Test nearest car park with no available lot")
  public void testGetNearestCarParks_WithNoAvailableLots() {
    double latitude = 1.5;
    double longitude = 1.5;
    CarPark carPark = new CarPark("CP002", "Address 2", 2.0, 2.0);
    CarparkInfo carparkInfo = new CarparkInfo("CP002", 100, 0, OffsetDateTime.now());
    when(carParkRepository.findByLocation(latitude, longitude)).thenReturn(Collections.singletonList(carPark));
    when(carparkInfoRepository.findByCarParkNumber("CP002")).thenReturn(carparkInfo);

    List<CarParkDto> carParkDtos = carParkService.getNearestCarParks(latitude, longitude, 1, 10);

    assertThat(carParkDtos).isEmpty();
  }
}

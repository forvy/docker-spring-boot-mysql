package com.forvy.spring.datajpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.forvy.spring.datajpa.model.Coordinates;
import com.forvy.spring.datajpa.util.ConvertSVY21;

public class CoordinateConversionServiceTest {

  private CoordinateConversionService coordinateConversionService;

  @BeforeEach
  public void setUp() {
    ConvertSVY21 mockConverter = new ConvertSVY21();
    coordinateConversionService = new CoordinateConversionService(mockConverter);
  }

  @Test
  @DisplayName("Test convert SVY21 to Coordinates")
  public void testConvertSvy21ToCoordinates() {
    double xCoord = 34955.3741;
    double yCoord = 39587.9068;
    Coordinates expectedCoordinates = new Coordinates(1.37429, 103.896);

    Coordinates result = coordinateConversionService.convertSvy21ToCoordinates(xCoord, yCoord);

    assertEquals(expectedCoordinates.getLatitude(), result.getLatitude(), 0.0001);
    assertEquals(expectedCoordinates.getLongitude(), result.getLongitude(), 0.0001);
  }
}

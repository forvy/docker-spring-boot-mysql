package com.forvy.spring.datajpa.service;

import org.springframework.stereotype.Service;

import com.forvy.spring.datajpa.model.Coordinates;
import com.forvy.spring.datajpa.util.ConvertSVY21;

@Service
public class CoordinateConversionService {
  private final ConvertSVY21 converter;

  public CoordinateConversionService(ConvertSVY21 converter) {
    this.converter = converter;
  }

  public Coordinates convertSvy21ToCoordinates(double xCoord, double yCoord) {
    Coordinates result = converter.computeLatLon(yCoord, xCoord);
    return result;
  }
}
package com.forvy.spring.datajpa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarParkDto {
  @JsonIgnore
  private String carParkNumber;
  private String address;
  private double latitude;
  private double longitude;
  private int totalLots;
  private int availableLots;

  public CarParkDto() {
  }

  public CarParkDto(String carParkNumber, String address, double latitude, double longitude, int totalLots,
      int availableLots) {
    this.carParkNumber = carParkNumber;
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.totalLots = totalLots;
    this.availableLots = availableLots;
  }

  public String getCarParkNumber() {
    return carParkNumber;
  }

  public void setCarParkNumber(String carParkNumber) {
    this.carParkNumber = carParkNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public int getTotalLots() {
    return totalLots;
  }

  public void setTotalLots(int totalLots) {
    this.totalLots = totalLots;
  }

  public int getAvailableLots() {
    return availableLots;
  }

  public void setAvailableLots(int availableLots) {
    this.availableLots = availableLots;
  }
}
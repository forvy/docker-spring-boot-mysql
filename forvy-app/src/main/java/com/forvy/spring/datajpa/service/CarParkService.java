package com.forvy.spring.datajpa.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forvy.spring.datajpa.dto.CarParkDto;
import com.forvy.spring.datajpa.model.CarPark;
import com.forvy.spring.datajpa.model.CarparkInfo;
import com.forvy.spring.datajpa.repository.CarParkRepository;
import com.forvy.spring.datajpa.repository.CarparkInfoRepository;
import com.forvy.spring.datajpa.util.GeoUtils;

@Service
public class CarParkService {

  @Autowired
  private CarParkRepository carParkRepository;

  @Autowired
  private CarparkInfoRepository carparkInfoRepository;

  public List<CarParkDto> getNearestCarParks(double latitude, double longitude, int page, int perPage) {
    List<CarPark> allCarParks = carParkRepository.findByLocation(latitude, longitude);

    List<CarPark> filteredCarParks = allCarParks.stream()
        .filter(carPark -> {
          CarparkInfo carparkInfo = carparkInfoRepository.findByCarParkNumber(carPark.getCarParkNo());
          return carparkInfo != null && carparkInfo.getAvailableLots() > 0;
        })
        .collect(Collectors.toList());

    filteredCarParks.sort(Comparator.comparingDouble(
        carPark -> GeoUtils.calculateDistance(latitude, longitude, carPark.getLatitude(), carPark.getLongitude())));

    int startIndex = (page - 1) * perPage;
    int endIndex = Math.min(startIndex + perPage, filteredCarParks.size());
    List<CarPark> paginatedCarParks = filteredCarParks.subList(startIndex, endIndex);

    List<CarParkDto> carParkDtos = paginatedCarParks.stream()
        .map(carPark -> {
          CarparkInfo carparkInfo = carparkInfoRepository.findByCarParkNumber(carPark.getCarParkNo());
          return convertToDto(carPark, carparkInfo);
        })
        .collect(Collectors.toList());

    return carParkDtos;
  }

  private CarParkDto convertToDto(CarPark carPark, CarparkInfo carparkInfo) {
    CarParkDto dto = new CarParkDto();
    dto.setAddress(carPark.getAddress());
    dto.setLatitude(carPark.getLatitude());
    dto.setLongitude(carPark.getLongitude());

    if (carparkInfo != null) {
      dto.setTotalLots(carparkInfo.getTotalLots());
      dto.setAvailableLots(carparkInfo.getAvailableLots());
    } else {
      dto.setTotalLots(0);
      dto.setAvailableLots(0);
    }

    return dto;
  }
}

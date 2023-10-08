package com.forvy.spring.datajpa.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forvy.spring.datajpa.model.CarPark;
import com.forvy.spring.datajpa.util.GeoUtils;

public interface CarParkRepository extends JpaRepository<CarPark, Long> {

  CarPark findByCarParkNo(String carParkNo);

  default List<CarPark> findByLocation(double latitude, double longitude) {
    List<CarPark> allCarParks = findAll();

    List<CarPark> filteredCarParks = allCarParks.stream()
        .filter(carPark -> GeoUtils.calculateDistance(latitude, longitude, carPark.getLatitude(),
            carPark.getLongitude()) <= 10)
        .collect(Collectors.toList());

    return filteredCarParks;
  }
}

package com.forvy.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forvy.spring.datajpa.model.CarparkInfo;

public interface CarparkInfoRepository extends JpaRepository<CarparkInfo, Long> {
  CarparkInfo findByCarParkNumber(String carParkNumber);

  List<CarparkInfo> findByCarParkNumberIn(List<String> carParkNumbers);

}
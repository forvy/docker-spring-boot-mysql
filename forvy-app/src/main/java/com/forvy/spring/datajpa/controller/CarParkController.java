package com.forvy.spring.datajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forvy.spring.datajpa.dto.CarParkDto;
import com.forvy.spring.datajpa.service.CarParkService;
import com.forvy.spring.datajpa.service.CarparkInfoService;
import com.forvy.spring.datajpa.service.CsvDataService;

@RestController
@RequestMapping("/carparks")
public class CarParkController {
	@Autowired
	private CarParkService carParkService;

	@Autowired
	private CsvDataService csvDataService;

	@Autowired
	private CarparkInfoService carparkInfoService;

	@GetMapping("/nearest")
	public List<CarParkDto> getNearestCarParks(
			@RequestParam double latitude,
			@RequestParam double longitude,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int per_page) {

		List<CarParkDto> nearestCarParks = carParkService.getNearestCarParks(latitude, longitude, page, per_page);
		return nearestCarParks;
	}

	@PostMapping("/updateData")
	public String updateData() {
		try {
			carparkInfoService.importJsonData();
			csvDataService.importCsvData("data/carpark_data.csv");
			return "Import Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Import Failed";
		}
	}
}
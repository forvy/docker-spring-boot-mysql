package com.forvy.spring.datajpa.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.forvy.spring.datajpa.model.CarPark;
import com.forvy.spring.datajpa.model.Coordinates;
import com.forvy.spring.datajpa.repository.CarParkRepository;

@Service
public class CsvDataService {
  @Autowired
  private CarParkRepository carParkRepository;
  @Autowired
  private CoordinateConversionService coordinateConversionService;
  @Autowired
  private ResourceLoader resourceLoader;

  public void importCsvData(String csvResourcePath) {
    try {
      Resource resource = resourceLoader.getResource("classpath:" + csvResourcePath);
      InputStreamReader reader = new InputStreamReader(resource.getInputStream());
      List<CarPark> carParks = new ArrayList<>();
      CSVParser csvParser = CSVFormat.DEFAULT
          .withFirstRecordAsHeader()
          .parse(reader);

      for (CSVRecord csvRecord : csvParser) {
        String carParkNo = csvRecord.get("car_park_no");
        String address = csvRecord.get("address");
        double xCoord = Double.parseDouble(csvRecord.get("x_coord"));
        double yCoord = Double.parseDouble(csvRecord.get("y_coord"));

        CarPark existingCarPark = carParkRepository.findByCarParkNo(carParkNo);

        if (existingCarPark == null) {
          Coordinates coordinates = coordinateConversionService.convertSvy21ToCoordinates(xCoord, yCoord);
          CarPark carPark = new CarPark(carParkNo, address, coordinates.getLatitude(), coordinates.getLongitude());
          carParks.add(carPark);
        }
      }

      carParkRepository.saveAll(carParks);

      reader.close();
      csvParser.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
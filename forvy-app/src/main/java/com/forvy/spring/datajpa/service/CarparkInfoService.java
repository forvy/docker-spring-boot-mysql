package com.forvy.spring.datajpa.service;

import java.io.IOException;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forvy.spring.datajpa.model.CarparkInfo;
import com.forvy.spring.datajpa.repository.CarparkInfoRepository;

@Service
public class CarparkInfoService {
  @Value("${carpark.api.url}")
  private String carparkApiUrl;

  private final CarparkInfoRepository carparkInfoRepository;

  public CarparkInfoService(CarparkInfoRepository carparkInfoRepository) {
    this.carparkInfoRepository = carparkInfoRepository;
  }

  public void importJsonData() {
    try {
      String jsonData = fetchCarparkDataFromApi();
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(jsonData);

      JsonNode itemsNode = rootNode.get("items");
      if (itemsNode != null && itemsNode.isArray()) {
        for (JsonNode itemNode : itemsNode) {
          JsonNode carparkDataNode = itemNode.get("carpark_data");
          if (carparkDataNode != null && carparkDataNode.isArray()) {
            for (JsonNode dataNode : carparkDataNode) {
              String carparkNumber = dataNode.get("carpark_number").asText();
              int totalLots = Integer.parseInt(dataNode.get("carpark_info")
                  .get(0)
                  .get("total_lots")
                  .asText());
              int availableLots = Integer.parseInt(dataNode.get("carpark_info")
                  .get(0)
                  .get("lots_available")
                  .asText());
              OffsetDateTime timestamp = OffsetDateTime.parse(dataNode.get("update_datetime").asText() + "+08:00");

              CarparkInfo existingCarparkInfo = carparkInfoRepository.findByCarParkNumber(carparkNumber);

              if (existingCarparkInfo == null) {
                CarparkInfo carparkInfo = new CarparkInfo(
                    carparkNumber,
                    totalLots,
                    availableLots,
                    timestamp);

                carparkInfoRepository.save(carparkInfo);
              } else if (timestamp.isAfter(existingCarparkInfo.getUpdateDatetime())) {
                existingCarparkInfo.setTotalLots(totalLots);
                existingCarparkInfo.setAvailableLots(availableLots);
                existingCarparkInfo.setUpdateDatetime(timestamp);

                carparkInfoRepository.save(existingCarparkInfo);
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String fetchCarparkDataFromApi() throws IOException {
    String jsonData = "";

    java.net.URL url = new java.net.URL(carparkApiUrl);
    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();

    if (conn.getResponseCode() == 200) {
      java.io.InputStream inputStream = conn.getInputStream();
      jsonData = new String(inputStream.readAllBytes());
    }

    conn.disconnect();

    return jsonData;
  }
}
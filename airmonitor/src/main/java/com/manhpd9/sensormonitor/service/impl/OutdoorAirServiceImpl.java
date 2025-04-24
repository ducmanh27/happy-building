package com.manhpd9.sensormonitor.service.impl;

import com.manhpd9.sensormonitor.dto.OutdoorAirDto;
import com.manhpd9.sensormonitor.entity.OutdoorAir;
import com.manhpd9.sensormonitor.mapper.OutdoorAirMapper;
import com.manhpd9.sensormonitor.repository.OutdoorAirRepository;
import com.manhpd9.sensormonitor.service.IOutdoorAirService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@Getter
@Setter
@Service
public class OutdoorAirServiceImpl implements IOutdoorAirService {

    private final RestTemplate restTemplate;
    OutdoorAirRepository outdoorAirRepository;

    @Override
    @Scheduled(fixedRate = 90000)
    public void getOutdoorAirData() {
        String apiKey = "93e34bbdd21109078a123d27a9f4afe9";  // Replace with actual API key
        double lat = 21.002229;  // Latitude, changeable
        double lon = 105.829923; // Longitude, changeable

        String apiUrl = buildApiUrl(lat, lon, apiKey);

        try {
            OutdoorAirDto response = restTemplate.getForObject(apiUrl, OutdoorAirDto.class);

            if (response != null) {
                OutdoorAir entity = OutdoorAirMapper.mapToOutdoorAir(response, new OutdoorAir());
                outdoorAirRepository.save(entity);

                System.out.println("Saved in db: " + entity);
            } else {
                System.err.println("Don't save to db from API");
            }
        } catch (Exception e) {
            System.err.println("Error when request outdoor api: " + e.getMessage());
        }
    }

    private String buildApiUrl(double lat, double lon, String apiKey) {
        return UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();
    }
}

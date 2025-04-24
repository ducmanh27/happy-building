package com.manhpd9.sensormonitor.service;

import com.manhpd9.sensormonitor.dto.IndoorAirDto;
import org.springframework.stereotype.Service;

public interface ISensorMonitorService {
    void createIndoorAir(IndoorAirDto indoorAirDto);
    IndoorAirDto getIndoorAirCurrent(int buildingId, int roomNumber);
}

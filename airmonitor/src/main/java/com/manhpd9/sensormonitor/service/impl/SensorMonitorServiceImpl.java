package com.manhpd9.sensormonitor.service.impl;

import com.manhpd9.sensormonitor.dto.IndoorAirDto;
import com.manhpd9.sensormonitor.entity.IndoorAir;
import com.manhpd9.sensormonitor.entity.Room;
import com.manhpd9.sensormonitor.exception.IndoorAirRecordNotFoundException;
import com.manhpd9.sensormonitor.exception.RoomNotExistException;
import com.manhpd9.sensormonitor.mapper.IndoorAirMapper;
import com.manhpd9.sensormonitor.repository.IndoorAirRepository;
import com.manhpd9.sensormonitor.repository.RoomRepository;
import com.manhpd9.sensormonitor.service.ISensorMonitorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorMonitorServiceImpl implements ISensorMonitorService {

    @Autowired
    private IndoorAirRepository indoorAirRepository;

    @Autowired
    private RoomRepository roomRepository;

    /**
     * @param indoorAirDto
     */
    @Override
    public void createIndoorAir(IndoorAirDto indoorAirDto) {
        IndoorAir indoorAir = IndoorAirMapper.mapToIndoorAir(indoorAirDto, new IndoorAir());
        indoorAirRepository.save(indoorAir);
    }

    @Override
    public IndoorAirDto getIndoorAirCurrent(int buildingId, int roomNumber) {
        Room room = Optional.ofNullable(roomRepository.findByRoomNumberAndBuildingId(roomNumber, buildingId))
                .orElseThrow(() -> new RoomNotExistException("Room " + roomNumber + " in building id " + buildingId + " not exist"));

        long roomId = room.getId();
        IndoorAir latestIndoorAir = Optional.ofNullable(indoorAirRepository.findTopByRoomIdOrderByCreatedAtDesc(roomId))
                .orElseThrow(() -> new IndoorAirRecordNotFoundException("Indoor air record in room " + roomNumber + ", building id " + buildingId + " not found"));

        return IndoorAirMapper.mapToIndoorAirDto(latestIndoorAir, new IndoorAirDto());
    }
}

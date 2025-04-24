package com.manhpd9.sensormonitor.mapper;

import com.manhpd9.sensormonitor.dto.IndoorAirDto;
import com.manhpd9.sensormonitor.entity.Building;
import com.manhpd9.sensormonitor.entity.IndoorAir;
import com.manhpd9.sensormonitor.entity.Room;
import com.manhpd9.sensormonitor.exception.BuildingNotExistException;
import com.manhpd9.sensormonitor.exception.RoomNotExistException;
import com.manhpd9.sensormonitor.repository.BuildingRepository;
import com.manhpd9.sensormonitor.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class IndoorAirMapper {

    private static RoomRepository roomRepository;
    private static BuildingRepository buildingRepository;

    @Autowired
    public IndoorAirMapper(RoomRepository theRoomRepository, BuildingRepository theBuildingRepository) {
        roomRepository = theRoomRepository;
        buildingRepository = theBuildingRepository;
    }

    public static IndoorAirDto mapToIndoorAirDto(IndoorAir indoorAir, IndoorAirDto indoorAirDto) {
        indoorAirDto.setRoomNumber(indoorAir.getRoom().getRoomNumber());
        indoorAirDto.setFloorNumber(indoorAir.getRoom().getFloorNumber());
        indoorAirDto.setLongitude(indoorAir.getRoom().getBuilding().getLongitude());
        indoorAirDto.setLatitude(indoorAir.getRoom().getBuilding().getLatitude());
        indoorAirDto.setTemperature(indoorAir.getTemperature());
        indoorAirDto.setHumidity(indoorAir.getHumidity());
        indoorAirDto.setDust(indoorAir.getDust());
        indoorAirDto.setLight(indoorAir.getLight());
        indoorAirDto.setCo2(indoorAir.getCo2());
        indoorAirDto.setCreatedAt(indoorAir.getCreatedAt());
        return indoorAirDto;
    }

    public static IndoorAir mapToIndoorAir(IndoorAirDto indoorAirDto, IndoorAir indoorAir) {
        Building theBuilding = Optional.ofNullable(
                        buildingRepository.findByNearbyLocation(indoorAirDto.getLatitude(), indoorAirDto.getLongitude())
                )
                .orElseThrow(() ->
                        new BuildingNotExistException("Building at latitude " + indoorAirDto.getLatitude() + " and longitude " + indoorAirDto.getLongitude() + " not exist"));

        Optional<Room> room = Optional.ofNullable(roomRepository.findByRoomNumberAndFloorNumberAndBuilding(indoorAirDto.getRoomNumber(), indoorAirDto.getFloorNumber(),
                theBuilding));
        if (room.isEmpty()) {
            throw new RoomNotExistException("Room not exist with room number " + indoorAirDto.getRoomNumber() + " floor number "
                    + indoorAirDto.getFloorNumber() + " longitude " + indoorAirDto.getLongitude() + " latitude " + indoorAirDto.getLatitude());
        }
        indoorAir.setRoom(room.get());
        indoorAir.setTemperature(indoorAirDto.getTemperature());
        indoorAir.setHumidity(indoorAirDto.getHumidity());
        indoorAir.setDust(indoorAirDto.getDust());
        indoorAir.setLight(indoorAirDto.getLight());
        indoorAir.setCo2(indoorAirDto.getCo2());
        indoorAir.setCreatedAt(indoorAirDto.getCreatedAt());
        return indoorAir;
    }
}

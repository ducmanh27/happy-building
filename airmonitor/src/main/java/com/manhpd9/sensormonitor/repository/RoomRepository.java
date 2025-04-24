package com.manhpd9.sensormonitor.repository;


import com.manhpd9.sensormonitor.entity.Building;
import com.manhpd9.sensormonitor.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomNumberAndFloorNumberAndBuilding(
            long roomNumber, long floorNumber, Building building
    );

    Room findByRoomNumberAndBuildingId(int roomNumber, long buildingId);
}

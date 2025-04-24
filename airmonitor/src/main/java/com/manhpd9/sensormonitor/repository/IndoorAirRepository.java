package com.manhpd9.sensormonitor.repository;
import com.manhpd9.sensormonitor.dto.IndoorAirDto;
import com.manhpd9.sensormonitor.entity.IndoorAir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IndoorAirRepository extends JpaRepository<IndoorAir, Long> {
    IndoorAir findTopByRoomIdOrderByCreatedAtDesc(long room_id);
    IndoorAir findByCreatedAt(LocalDateTime createdAt);
}

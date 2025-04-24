package com.manhpd9.sensormonitor.repository;
import com.manhpd9.sensormonitor.entity.Building;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Building findByLatitudeAndLongitude(double latitude, double longitude);

    @Query("SELECT b FROM Building b WHERE ABS(b.latitude - :lat) < 0.01 AND ABS(b.longitude - :lon) < 0.01")
    Building findByNearbyLocation(@Param("lat") double latitude,
                                  @Param("lon") double longitude);
}

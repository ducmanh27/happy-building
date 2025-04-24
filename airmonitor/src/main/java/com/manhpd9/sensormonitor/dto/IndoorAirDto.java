package com.manhpd9.sensormonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "IndoorAir",
        description = "Schema to hold indoor air information"
)
public class IndoorAirDto {
    private double longitude;
    private double latitude;
    private long roomNumber;
    private long floorNumber;
    private double temperature;
    private double humidity;
    private double co2;
    private double dust;
    private double light;
    private LocalDateTime createdAt;
}

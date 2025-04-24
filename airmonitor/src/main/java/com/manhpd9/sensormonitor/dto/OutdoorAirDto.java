package com.manhpd9.sensormonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@Schema(
        name = "OutdoorAirInfo",
        description = "Schema to hold outdoor air information"
)
@Getter
@Setter
public class OutdoorAirDto {

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("main")
    private MainData main;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("dt")
    private long timestamp;

    @JsonProperty("timezone")
    private int timezone;  // New field for timezone

    public LocalDateTime getAdjustedTimestamp() {
        // Convert timestamp (in UTC) to LocalDateTime based on the timezone
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestamp),
                ZoneOffset.ofTotalSeconds(timezone)
        );
    }

    @Getter
    @Setter
    public static class Coord {
        private double lon;
        private double lat;
    }

    @Getter
    @Setter
    public static class MainData {
        private double temp;
        private double humidity;
        private double pressure;
    }

    @Getter
    @Setter
    public static class Wind {
        private double speed;
    }

    @Getter
    @Setter
    public static class Clouds {
        private int all;
    }
}

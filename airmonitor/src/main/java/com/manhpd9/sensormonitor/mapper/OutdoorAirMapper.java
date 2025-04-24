package com.manhpd9.sensormonitor.mapper;

import com.manhpd9.sensormonitor.dto.OutdoorAirDto;
import com.manhpd9.sensormonitor.entity.OutdoorAir;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class OutdoorAirMapper {
    public static OutdoorAir mapToOutdoorAir( OutdoorAirDto outdoorAirDto, OutdoorAir outdoorAir)
    {
        outdoorAir.setTemperature(outdoorAirDto.getMain().getTemp());
        outdoorAir.setHumidity(outdoorAirDto.getMain().getHumidity());
        outdoorAir.setPressure(outdoorAirDto.getMain().getPressure());
        outdoorAir.setWindSpeed(outdoorAirDto.getWind().getSpeed());
        outdoorAir.setClouds(outdoorAirDto.getClouds().getAll());
        outdoorAir.setLongitude(outdoorAirDto.getCoord().getLon());
        outdoorAir.setLatitude(outdoorAirDto.getCoord().getLat());
        outdoorAir.setCreatedAt(LocalDateTime.now());
        return outdoorAir;
    }
}

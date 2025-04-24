package com.manhpd9.sensormonitor.controller;

import com.manhpd9.sensormonitor.constants.SensorMonitorConstants;
import com.manhpd9.sensormonitor.dto.ErrorResponseDto;
import com.manhpd9.sensormonitor.dto.IndoorAirDto;
import com.manhpd9.sensormonitor.dto.ResponseDto;
import com.manhpd9.sensormonitor.service.ISensorMonitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Sensor Monitor",
        description = "CRUD REST APIs in Sensor Monitor to CREATE, UPDATE, FETCH AND DELETE indoor/outdoor air info"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class SensorMonitorController {

    private ISensorMonitorService iSensorMonitorService;

    @PostMapping("/indoor_air")
    public ResponseEntity<ResponseDto> createIndoorAirRecord(@Valid @RequestBody IndoorAirDto indoorAirDto) {
        iSensorMonitorService.createIndoorAir(indoorAirDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(SensorMonitorConstants.STATUS_201, SensorMonitorConstants.MESSAGE_201));
    }

    @GetMapping("/indoor_air")
    public ResponseEntity<IndoorAirDto> getIndoorAirCurrent(@RequestParam
                                                           @Min(value = 1, message = "Building ID must be greater than 0")
                                                           int buildingId,
                                                           @RequestParam
                                                           @Min(value = 101, message = "Room number must be greater than 100")
                                                           int roomNumber) {
        IndoorAirDto indoorAirDto = iSensorMonitorService.getIndoorAirCurrent(buildingId, roomNumber);
        return ResponseEntity.status(HttpStatus.OK).body(indoorAirDto);
    }
}

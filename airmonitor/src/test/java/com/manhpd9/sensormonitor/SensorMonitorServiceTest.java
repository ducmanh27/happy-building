package com.manhpd9.sensormonitor;

import com.manhpd9.sensormonitor.dto.IndoorAirDto;
import com.manhpd9.sensormonitor.entity.IndoorAir;
import com.manhpd9.sensormonitor.repository.IndoorAirRepository;
import com.manhpd9.sensormonitor.service.ISensorMonitorService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class SensorMonitorServiceTest {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    ISensorMonitorService iSensorMonitorService;

    @Autowired
    IndoorAirRepository indoorAirRepository;

    private
    IndoorAirDto indoorAirDto;

    @BeforeAll
    public static void setupBeforeAllTests() {

    }

    @BeforeEach
    public void setUpBeforeEachTest() {
        indoorAirDto = new IndoorAirDto();
        indoorAirDto.setLatitude(21.00);
        indoorAirDto.setLongitude(105.82);
        indoorAirDto.setRoomNumber(202);
        indoorAirDto.setDust(33);
        indoorAirDto.setTemperature(30);
        indoorAirDto.setHumidity(56);
        indoorAirDto.setCo2(44);
        indoorAirDto.setLight(666);
        indoorAirDto.setFloorNumber(2);
        indoorAirDto.setCreatedAt(LocalDateTime.parse("2024-04-02T14:30:00"));
    }

    @Test
    public void createIndoorAirService() {
        iSensorMonitorService.createIndoorAir(indoorAirDto);
        IndoorAir indoorAir = indoorAirRepository.findByCreatedAt(LocalDateTime.parse("2024-04-02T14:30:00"));
        assertEquals(LocalDateTime.parse("2024-04-02T14:30:00"), indoorAir.getCreatedAt());
    }

    @Test
    public void getIndoorAirCurrentService() {
        iSensorMonitorService.createIndoorAir(indoorAirDto);
        IndoorAirDto theIndoorAirDto = iSensorMonitorService.getIndoorAirCurrent(1, 202);
        assertNotEquals(null, theIndoorAirDto);
        assertEquals(202, theIndoorAirDto.getRoomNumber());
        assertEquals(LocalDateTime.parse("2024-04-02T14:30:00"), theIndoorAirDto.getCreatedAt());
    }

    @AfterEach
    public void tearDownAfterTest() {
        jdbc.execute("DELETE FROM indoor_air");
        jdbc.execute("ALTER TABLE indoor_air ALTER COLUMN id RESTART WITH 1;");
    }
}

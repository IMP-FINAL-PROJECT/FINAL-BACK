package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.entity.Sensor;
import com.imp.fluffy_mood.service.SensorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/insert")
    public void login(@RequestBody SensorDto sensorDto) {
        sensorService.insert(sensorDto);
    }


}

package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody SensorDto sensorDto) {
        sensorService.insert(sensorDto);
    }

    @GetMapping("/test")
    public ResponseEntity<Message> test(@RequestBody SensorDto sensorDto) {
        return sensorService.test(sensorDto);
    }


}

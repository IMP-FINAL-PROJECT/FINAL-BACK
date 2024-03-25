package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.SensorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Message> insert(HttpServletRequest request, @RequestBody SensorDto sensorDto) {
        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, illuminance : {}, pedometer : {}, screen_frequency : {}, screen_duration : {}, gps : {}, timestamp : {}, hour : {}",
                sensorDto.getId(), sensorDto.getIlluminance(), sensorDto.getPedometer(), sensorDto.getScreen_frequency(), sensorDto.getScreen_duration(), sensorDto.getGps(), sensorDto.getTimestamp(), sensorDto.getHour());

        ResponseEntity<Message> response = sensorService.insert(sensorDto);

        log.debug("Data : {}", response.getBody());

        return response;
    }

    @GetMapping("/test")
    public ResponseEntity<Message> test(HttpServletRequest request, @RequestBody SensorDto sensorDto) {
        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}", sensorDto.getId());

        ResponseEntity<Message> response = sensorService.test(sensorDto);

        log.debug("Data : {}", response.getBody());

        return response;
    }


}

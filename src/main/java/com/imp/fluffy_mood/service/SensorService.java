package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.entity.Sensor;
import com.imp.fluffy_mood.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public void insert(SensorDto sensorDto) {

        sensorDto.setTimestamp(LocalDateTime.now());
        Sensor sensor = sensorDto.toEntity();

        sensorRepository.save(sensor);

    }

}

package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.entity.Sensor;
import com.imp.fluffy_mood.entity.pk.SensorPK;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public void insert(SensorDto sensorDto) {

        Sensor sensor = sensorDto.toEntity();

        sensorRepository.save(sensor);

    }

    public ResponseEntity<Message> test(SensorDto sensorDto) {

        Message message = new Message();

        SensorPK user = new SensorPK(sensorDto.getId(), sensorDto.getTimestamp(), sensorDto.getHour());

        Optional<Sensor> sensor = sensorRepository.findById(user);

        message.setStatus(StatusEnum.OK.getStatusCode());
        message.setResult(true);
        message.setMessage("Success");
        message.setData(sensor);

        return ResponseEntity.ok(message);
    }

}

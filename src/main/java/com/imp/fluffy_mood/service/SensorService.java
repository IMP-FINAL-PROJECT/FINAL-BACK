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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public ResponseEntity<Message> insert(SensorDto sensorDto) {

        Message message = new Message();

        Sensor input = sensorRepository.findByIdAndTimestampAndHour(sensorDto.getId(), sensorDto.getTimestamp(), sensorDto.getHour());

        if(input == null) {

            sensorRepository.save(sensorDto.toEntity());

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");

        } else {

            SensorDto sensor = input.toDto();

            List<Integer> illuminance = sensor.getIlluminance();
            List<List<? extends Number>> gps = sensor.getGps();

            sensor.setPedometer(sensor.getPedometer() + sensorDto.getPedometer());
            sensor.setScreen_duration(sensor.getScreen_duration() + sensorDto.getScreen_duration());
            sensor.setScreen_frequency(sensor.getScreen_frequency() + sensorDto.getScreen_frequency());

            illuminance.addAll(sensorDto.getIlluminance());
            sensor.setIlluminance(illuminance);

            gps.addAll(sensorDto.getGps());
            sensor.setGps(gps);

            sensorRepository.save(sensor.toEntity());

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Failed Input Data.");
        }

        return ResponseEntity.ok(message);

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

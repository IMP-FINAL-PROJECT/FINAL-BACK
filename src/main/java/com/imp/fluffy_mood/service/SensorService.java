package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.entity.Sensor;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    // 센서 데이터 추가
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
            sensor.setScreenFrequency(sensor.getScreenFrequency() + sensorDto.getScreenFrequency());
            sensor.setScreenDuration(sensor.getScreenDuration() + sensorDto.getScreenDuration());
            sensor.setCallFrequency(sensor.getCallFrequency() + sensorDto.getCallFrequency());
            sensor.setCallDuration(sensor.getCallDuration() + sensorDto.getCallDuration());

            illuminance.addAll(sensorDto.getIlluminance());
            sensor.setIlluminance(illuminance);

            gps.addAll(sensorDto.getGps());
            sensor.setGps(gps);

            sensorRepository.save(sensor.toEntity());

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Update Data.");

        }

        return ResponseEntity.ok(message);

    }

    public ResponseEntity<Message> test(SensorDto sensorDto) {

        Message message = new Message();

        Sensor sensor = sensorRepository.findByIdAndTimestampAndHour(sensorDto.getId(), sensorDto.getTimestamp(), sensorDto.getHour());

        message.setStatus(StatusEnum.OK.getStatusCode());
        message.setResult(true);
        message.setMessage("Success");
        message.setData(sensor);

        return ResponseEntity.ok(message);
    }

}

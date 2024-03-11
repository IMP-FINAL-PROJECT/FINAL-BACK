package com.imp.fluffy_mood.dto;

import com.imp.fluffy_mood.entity.Sensor;
import com.imp.fluffy_mood.entity.converter.IntegerStringConverter;
import com.imp.fluffy_mood.entity.converter.ListStringConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {

    private String id; // 사용자 아이디 (e-mail)
    private List<Integer> illuminance; // 조도 센서
    private int pedometer; // 만보기
    private int screenFrequency; // 화면 빈도 수
    private int screenDuration; // 화면 사용 시간
    private List<List<? extends Number>> gps; // gps
    private LocalDate timestamp; // 날짜
    private int hour; // 시간

    public Sensor toEntity() {
        return Sensor.builder()
                .id(id)
                .illuminance(illuminance)
                .pedometer(pedometer)
                .screenFrequency(screenFrequency)
                .screenDuration(screenDuration)
                .gps(gps)
                .timestamp(timestamp)
                .hour(hour)
                .build();
    }

}

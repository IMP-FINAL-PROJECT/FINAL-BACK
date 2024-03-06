package com.imp.fluffy_mood.dto;

import com.imp.fluffy_mood.entity.Sensor;
import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {

    private ObjectId id;
    private String user; // 사용자 ID
    private int illuminance; // 조도 센서
    private Boolean screenState; // 화면 on/off
    private int pedometer; // 만보기
    private Double latitude; // 위도
    private Double longitude; // 경도
    private LocalDateTime timestamp; // 시간

    public Sensor toEntity() {
        return Sensor.builder()
                .id(id)
                .user(user)
                .illuminance(illuminance)
                .screenState(screenState)
                .pedometer(pedometer)
                .latitude(latitude)
                .longitude(longitude)
                .timestamp(timestamp)
                .build();
    }

}

package com.imp.fluffy_mood.dto;

import com.imp.fluffy_mood.entity.Sensor;
import lombok.*;

import java.time.LocalDate;
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
    private int screen_frequency; // 화면 빈도 수
    private int screen_duration; // 화면 사용 시간
    private List<List<? extends Number>> gps; // gps
    private LocalDate timestamp; // 날짜
    private int hour; // 시간

    public Sensor toEntity() {
        return Sensor.builder()
                .id(id)
                .illuminance(illuminance)
                .pedometer(pedometer)
                .screen_frequency(screen_frequency)
                .screen_duration(screen_duration)
                .gps(gps)
                .timestamp(timestamp)
                .hour(hour)
                .build();
    }

}

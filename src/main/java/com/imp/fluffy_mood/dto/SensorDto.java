package com.imp.fluffy_mood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private int number;
    private String id; // 사용자 아이디 (e-mail)
    private List<Integer> illuminance; // 조도 센서
    private int pedometer; // 만보기
    @JsonProperty("screen_frequency")
    private int screenFrequency; // 화면 빈도 수
    @JsonProperty("screen_duration")
    private int screenDuration; // 화면 사용 시간
    private List<List<? extends Number>> gps; // gps
    private LocalDate timestamp; // 날짜
    private int hour; // 시간

    public Sensor toEntity() {
        return Sensor.builder()
                .number(number)
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

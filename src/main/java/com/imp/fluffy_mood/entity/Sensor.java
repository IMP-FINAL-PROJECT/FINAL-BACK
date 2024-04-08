package com.imp.fluffy_mood.entity;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.entity.converter.IntegerStringConverter;
import com.imp.fluffy_mood.entity.converter.ListStringConverter;
import com.imp.fluffy_mood.entity.pk.SensorPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Table(name = "sensor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//@IdClass(SensorPK.class)
public class Sensor implements Serializable {

    @Id
    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "id")
    private String id; // 사용자 아이디 (e-mail)

    @Column(name = "illuminance", length = 500)
    @Convert(converter = IntegerStringConverter.class)
    private List<Integer> illuminance; // 조도 센서

    @Column(name = "pedometer")
    private int pedometer; // 만보기

    @Column(name = "screen_frequency")
    private int screenFrequency; // 화면 빈도 수

    @Column(name = "screen_duration")
    private int screenDuration; // 화면 사용 시간

    @Column(name = "phone_frequency")
    private int phoneFrequency; // 전화 빈도 수

    @Column(name = "phone_duration")
    private int phoneDuration; // 전화 사용 시간

    @Column(name = "gps", length = 10000)
    @Convert(converter = ListStringConverter.class)
    private List<List<? extends Number>> gps; // gps

    @Column(name = "timestamp")
    private LocalDate timestamp; // 날짜

    @Column(name = "hour")
    private int hour; // 시간

    public SensorDto toDto() {
        return SensorDto.builder()
                .number(number)
                .id(id)
                .illuminance(illuminance)
                .pedometer(pedometer)
                .screenFrequency(screenFrequency)
                .screenDuration(screenDuration)
                .phoneFrequency(phoneFrequency)
                .phoneDuration(phoneDuration)
                .gps(gps)
                .timestamp(timestamp)
                .hour(hour)
                .build();
    }

}

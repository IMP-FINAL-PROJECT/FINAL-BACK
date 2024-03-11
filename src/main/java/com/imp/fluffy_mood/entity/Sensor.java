package com.imp.fluffy_mood.entity;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.dto.UserDto;
import com.imp.fluffy_mood.entity.converter.IntegerStringConverter;
import com.imp.fluffy_mood.entity.converter.ListStringConverter;
import com.imp.fluffy_mood.entity.pk.SensorPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sensor")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@IdClass(SensorPK.class)
public class Sensor implements Serializable {

    @Id
    private String id; // 사용자 아이디 (e-mail)

    @Column(name = "illuminance")
    @Convert(converter = IntegerStringConverter.class)
    private List<Integer> illuminance; // 조도 센서

    @Column(name = "pedometer")
    private int pedometer; // 만보기

    @Column(name = "screen_frequency")
    private int screenFrequency; // 화면 빈도 수

    @Column(name = "screen_duration")
    private int screenDuration; // 화면 사용 시간

    @Column(name = "gps")
    @Convert(converter = ListStringConverter.class)
    private List<List<? extends Number>> gps; // gps

    @Id
    @Column(name = "timestamp")
    private LocalDate timestamp; // 날짜

    @Id
    @Column(name = "hour")
    private int hour; // 시간

    private SensorDto toDto() {
        return SensorDto.builder()
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

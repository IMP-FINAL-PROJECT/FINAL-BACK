package com.imp.fluffy_mood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.imp.fluffy_mood.dto.AnalysisDto;
import com.imp.fluffy_mood.entity.converter.IntegerStringConverter;
import com.imp.fluffy_mood.entity.converter.ListStringConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sensor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Analysis {

    @Id
    @JsonIgnore
    private int number;

    private String id; // 사용자 아이디 (e-mail)

    @Convert(converter = IntegerStringConverter.class)
    private List<Integer> illuminance; // 조도 센서

    private int pedometer; // 만보기

    @Column(name = "screen_frequency")
    private int screenFrequency; // 화면 빈도 수

    @Column(name = "screen_duration")
    private int screenDuration; // 화면 사용 시간

    @Convert(converter = ListStringConverter.class)
    private List<List<? extends Number>> gps; // gps

    private LocalDate timestamp; // 날짜

    public AnalysisDto toDto() {
        return AnalysisDto.builder()
                .id(id)
                .illuminance(illuminance)
                .pedometer(pedometer)
                .screenDuration(screenDuration)
                .screenFrequency(screenFrequency)
                .gps(gps)
                .timestamp(timestamp)
                .build();
    }

}

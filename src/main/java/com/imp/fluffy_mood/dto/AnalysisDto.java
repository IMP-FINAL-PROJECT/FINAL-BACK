package com.imp.fluffy_mood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imp.fluffy_mood.entity.Analysis;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisDto {

    private String id; // 사용자 아이디 (e-mail)

    private List<Integer> illuminance; // 조도 평균

    private int pedometer; // 만보기

    @JsonProperty("pedometer_list")
    private List<Integer> pedometerList; // 만보기 시간대 별 list

    @JsonProperty("screen_frequency")
    private int screenFrequency; // 화면 총 빈도 수

    @JsonProperty("screen_frequency_list")
    private List<Integer> screenFrequencyList; // 화면 빈도 수 시간대 별 list

    @JsonProperty("screen_duration")
    private int screenDuration; // 화면 총 사용 시간

    @JsonProperty("screen_duration_list")
    private List<Integer> screenDurationList; // 화면 사용 시간 시간대 별 list

    private List<List<? extends Number>> gps; // gps

    @JsonProperty("date")
    private LocalDate timestamp = LocalDate.now();

    public Analysis toEntity() {
        return Analysis.builder()
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

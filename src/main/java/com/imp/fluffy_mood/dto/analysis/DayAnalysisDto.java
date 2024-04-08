package com.imp.fluffy_mood.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DayAnalysisDto {

    @JsonProperty("illuminance")
    private List<Integer> illuminance; // 일 조도 평균

    @JsonProperty("pedometer")
    private int pedometer; // 일 만보기

    @JsonProperty("pedometer_list")
    private List<Integer> pedometerList; // 일 만보기 시간대 별 list

    @JsonProperty("screen_frequency")
    private int screenFrequency; // 일 화면 총 빈도 수

    @JsonProperty("screen_frequency_list")
    private List<Integer> screenFrequencyList; // 화면 빈도 수 시간대 별 list

    @JsonProperty("screen_duration")
    private int screenDuration; // 일 화면 총 사용 시간

    @JsonProperty("screen_duration_list")
    private List<Integer> screenDurationList; // 일 화면 사용 시간 시간대 별 list

    @JsonProperty("phone_frequency")
    private int phoneFrequency; // 일 전화 횟수

    @JsonProperty("phone_frequency_list")
    private List<Integer> phoneFrequencyList; // 일 화면 사용 시간 시간대 별 list

    @JsonProperty("phone_duration")
    private int phoneDuration; // 일 전화 시간

    @JsonProperty("phone_duration_list")
    private List<Integer> phoneDurationList; // 일 화면 사용 시간 시간대 별 list

}

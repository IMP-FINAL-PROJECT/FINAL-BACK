package com.imp.fluffy_mood.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WeekAnalysisDto {

    @JsonProperty("illuminance")
    private List<Integer> illuminance; // 주 조도 평균

    @JsonProperty("pedometer_list")
    private List<Integer> pedometerList; // 주 만보기 list

    @JsonProperty("screen_frequency_list")
    private List<Integer> screenFrequencyList; // 화면 빈도 수 시간대 별 list

    @JsonProperty("screen_duration_list")
    private List<Integer> screenDurationList; // 주 화면 사용 시간 시간대 별 list

    @JsonProperty("call_frequency_list")
    private List<Integer> callFrequencyList; // 주 전화 사용 빈도 list

    @JsonProperty("call_duration_list")
    private List<Integer> callDurationList; // 주 전화 사용 시간 list
}

package com.imp.fluffy_mood.dto;

import com.imp.fluffy_mood.entity.Happiness;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HappinessDto {

    private String id;
    private int point;
    private String aiAnalysis;
    private LocalDate timestamp;

    public Happiness toEntity() {
        return Happiness.builder()
                .id(id)
                .point(point)
                .aiAnalysis(aiAnalysis)
                .timestamp(timestamp)
                .build();
    }
}

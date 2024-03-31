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
    private LocalDate timestamp;
    private int hour;

    public Happiness toEntity() {
        return Happiness.builder()
                .id(id)
                .point(point)
                .timestamp(timestamp)
                .build();
    }
}

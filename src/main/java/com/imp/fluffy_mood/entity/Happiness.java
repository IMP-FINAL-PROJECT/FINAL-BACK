package com.imp.fluffy_mood.entity;

import com.imp.fluffy_mood.dto.HappinessDto;
import com.imp.fluffy_mood.entity.pk.HappinessPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "happiness")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@IdClass(HappinessPK.class)
public class Happiness {

    @Id
    private String id;

    @Column(name = "point", nullable = false)
    private int point;

    @Column(name = "ai_analysis")
    private String aiAnalysis;

    @Id
    private LocalDate timestamp;

    public HappinessDto toDto() {
        return HappinessDto.builder()
                .id(id)
                .point(point)
                .aiAnalysis(aiAnalysis)
                .timestamp(timestamp)
                .build();
    }

}

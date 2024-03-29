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

    @Id
    private LocalDate timestamp;

    @Id
    private int hour;

    public HappinessDto toDto() {
        return HappinessDto.builder()
                .id(id)
                .point(point)
                .timestamp(timestamp)
                .hour(hour)
                .build();
    }

}

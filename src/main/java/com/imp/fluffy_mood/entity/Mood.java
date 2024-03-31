package com.imp.fluffy_mood.entity;

import com.imp.fluffy_mood.dto.MoodDto;
import com.imp.fluffy_mood.entity.pk.MoodPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "mood")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@IdClass(MoodPK.class)
public class Mood {

    @Id
    @Column(name = "id", length = 60, nullable = false)
    private String id;

    @Column(name = "score")
    private int score; // 점수

    @Id
    @Column(name = "timestamp", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime timestamp;

    public MoodDto toDto() {
        return MoodDto.builder()
                .id(id)
                .score(score)
                .timestamp(timestamp)
                .build();
    }
}

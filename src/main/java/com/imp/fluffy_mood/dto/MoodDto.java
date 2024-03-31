package com.imp.fluffy_mood.dto;

import com.imp.fluffy_mood.entity.Mood;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoodDto {

    private String id;
    private int score;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Mood toEntity() {
        return Mood.builder()
                .id(id)
                .score(score)
                .timestamp(timestamp)
                .build();
    }
}

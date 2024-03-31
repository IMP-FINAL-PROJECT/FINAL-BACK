package com.imp.fluffy_mood.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoodPK implements Serializable {

    private String id;
    private LocalDateTime timestamp;

}

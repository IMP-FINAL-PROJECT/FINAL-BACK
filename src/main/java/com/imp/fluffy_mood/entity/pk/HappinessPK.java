package com.imp.fluffy_mood.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HappinessPK implements Serializable {

    private String id;
    private LocalDate timestamp;
    private int hour;
}

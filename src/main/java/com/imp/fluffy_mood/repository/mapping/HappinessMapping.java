package com.imp.fluffy_mood.repository.mapping;

import java.time.LocalDate;

public interface HappinessMapping {

    String getId();
    int getPoint();
    LocalDate getTimestamp();
}

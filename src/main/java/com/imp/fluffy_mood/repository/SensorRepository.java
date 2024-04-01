package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.dto.AnalysisDto;
import com.imp.fluffy_mood.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Sensor findTopByIdOrderByTimestampDescHourDesc(String id);
    Sensor findByIdAndTimestampAndHour(String id, LocalDate timestamp, int hour);
}

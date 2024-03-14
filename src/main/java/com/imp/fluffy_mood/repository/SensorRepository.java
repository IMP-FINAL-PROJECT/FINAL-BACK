package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.Sensor;
import com.imp.fluffy_mood.entity.pk.SensorPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface SensorRepository extends JpaRepository<Sensor, SensorPK> {

    Sensor findByIdAndTimestampAndHour(String id, LocalDate timestamp, int hour);
}

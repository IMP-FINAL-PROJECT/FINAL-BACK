package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.entity.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, String> {

}

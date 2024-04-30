package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    List<Log> findByIdAndTimestamp(String id, LocalDate timestamp);

    List<Log> findByIdAndTimestampBetween(String id, LocalDate start, LocalDate end);

}

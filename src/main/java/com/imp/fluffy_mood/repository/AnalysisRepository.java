package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Integer> {

    List<Analysis> findByIdAndTimestamp(String id, LocalDate timestamp);

    List<Analysis> findByIdAndTimestampBetween(String id, LocalDate start, LocalDate end);

}

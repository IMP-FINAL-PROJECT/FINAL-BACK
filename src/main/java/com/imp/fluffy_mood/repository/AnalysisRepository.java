package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.Analysis;
import com.imp.fluffy_mood.entity.pk.AnalysisPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, AnalysisPK> {

    Analysis findByIdAndDate(String id, LocalDate date);

}

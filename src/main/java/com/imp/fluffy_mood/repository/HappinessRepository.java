package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.Happiness;
import com.imp.fluffy_mood.entity.pk.HappinessPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface HappinessRepository extends JpaRepository<Happiness, HappinessPK> {

    Happiness findTopByIdOrderByTimestampDesc(String id);

    List<Happiness> findByIdAndTimestampBetween(String id, LocalDate start, LocalDate end);

}

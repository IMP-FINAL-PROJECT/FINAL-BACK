package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.Happiness;
import com.imp.fluffy_mood.entity.pk.HappinessPK;
import com.imp.fluffy_mood.repository.mapping.HappinessMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HappinessRepository extends JpaRepository<Happiness, HappinessPK> {

    @Query(value = "SELECT ai_analysis FROM happiness WHERE id = :id AND timestamp = :date", nativeQuery = true)
    Optional<String> findByIdAndTimestamp(String id, LocalDate date);

    List<HappinessMapping> findByIdAndTimestampBetween(String id, LocalDate start, LocalDate end);

}
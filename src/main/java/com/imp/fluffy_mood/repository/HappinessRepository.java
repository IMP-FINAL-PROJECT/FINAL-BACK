package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.Happiness;
import com.imp.fluffy_mood.entity.pk.HappinessPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HappinessRepository extends JpaRepository<Happiness, HappinessPK> {

    Happiness findTopByIdOrderByTimestampDescHourDesc(String id);

}

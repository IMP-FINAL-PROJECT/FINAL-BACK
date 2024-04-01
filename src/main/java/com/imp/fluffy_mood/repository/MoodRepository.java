package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.Mood;
import com.imp.fluffy_mood.entity.pk.MoodPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends JpaRepository<Mood, MoodPK> {

    Mood findTopByIdOrderByTimestampDesc(String id);
}

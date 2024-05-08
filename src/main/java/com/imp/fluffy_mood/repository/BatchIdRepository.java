package com.imp.fluffy_mood.repository;

import com.imp.fluffy_mood.entity.BatchId;
import com.imp.fluffy_mood.entity.pk.BatchIdPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchIdRepository extends JpaRepository<BatchId, BatchIdPK> {

    BatchId findById(String id);
}

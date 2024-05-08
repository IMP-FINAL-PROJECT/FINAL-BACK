package com.imp.fluffy_mood.entity;

import com.imp.fluffy_mood.entity.pk.BatchIdPK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "batch_id")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@IdClass(BatchIdPK.class)
public class BatchId {

    @Id
    private int number;

    @Id
    private String id;

}

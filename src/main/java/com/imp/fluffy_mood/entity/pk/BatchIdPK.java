package com.imp.fluffy_mood.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchIdPK implements Serializable {
    private int number;
    private String id;
}

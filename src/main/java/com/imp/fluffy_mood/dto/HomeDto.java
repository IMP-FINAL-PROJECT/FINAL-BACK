package com.imp.fluffy_mood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeDto {

    private String id;
    private int point;
    @JsonProperty("point_list")
    private List<Integer> pointList;

}

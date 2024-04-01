package com.imp.fluffy_mood.dto;

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
    private int score;
    //private List<List<? extends Number>> gps; // gps

}

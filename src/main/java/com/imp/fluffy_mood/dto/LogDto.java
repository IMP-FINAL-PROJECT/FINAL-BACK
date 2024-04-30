package com.imp.fluffy_mood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imp.fluffy_mood.dto.log.DayLogDto;
import com.imp.fluffy_mood.dto.log.WeekLogDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDto {

    private String id; // 사용자 아이디 (e-mail)

    private DayLogDto day; // 일 데이터

    private WeekLogDto week; // 주 데이터

    private List<List<? extends Number>> gps; // gps

    @JsonProperty("date")
    private LocalDate timestamp;

}

package com.imp.fluffy_mood.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.imp.fluffy_mood.entity.Analysis;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisDto {


    private String id; // 사용자 아이디 (e-mail)

    @JsonProperty("place_diversity")
    private List<List<? extends Number>> placeDiversity;

    @JsonProperty("home_stay_percentage")
    private float homeStayPercentage;

    @JsonProperty("life_routine_consistency")
    private float lifeRoutineConsistency;

    @JsonProperty("day_phone_use_frequency")
    private float dayPhoneUseFrequency;

    @JsonProperty("night_phone_use_frequency")
    private float nightPhoneUseFrequency;

    @JsonProperty("day_phone_use_duration")
    private int dayPhoneUseDuration;

    @JsonProperty("night_phone_use_duration")
    private int nightPhoneUseDuration;

    @JsonProperty("day_call_use_frequency")
    private float dayCallUseFrequency;

    @JsonProperty("night_call_use_frequency")
    private float nightCallUseFrequency;

    @JsonProperty("day_call_use_duration")
    private float dayCallUseDuration;

    @JsonProperty("night_call_use_duration")
    private float nightCallUseDuration;

    @JsonProperty("day_light_exposure")
    private float dayLightExposure;

    @JsonProperty("night_light_exposure")
    private float nightLightExposure;

    @JsonProperty("day_step_count")
    private float dayStepCount;

    @JsonProperty("night_step_count")
    private float nightStepCount;

    @JsonProperty("activity_score")
    private float activityScore;

    @JsonProperty("phone_usage_score")
    private float phoneUsageScore;

    @JsonProperty("illumination_exposure_score")
    private float illuminationExposureScore;

    @JsonProperty("location_diversity_score")
    private float locationDiversityScore;

    @JsonProperty("circadian_rhythm_score")
    private float circadianRhythmScore;

    @JsonIgnore
    private int dayTimeCount;

    @JsonIgnore
    private int nightTimeCount;

    private LocalDate date;

    @JsonProperty("ai_analysis")
    private String aiAnalysis;

    public Analysis toEntity() {
        return Analysis.builder()
                .id(id)
                .placeDiversity(placeDiversity)
                .homeStayPercentage(homeStayPercentage)
                .lifeRoutineConsistency(lifeRoutineConsistency)
                .dayPhoneUseFrequency(dayPhoneUseFrequency)
                .nightPhoneUseFrequency(nightPhoneUseFrequency)
                .dayPhoneUseDuration(dayPhoneUseDuration)
                .nightPhoneUseDuration(nightPhoneUseDuration)
                .dayCallUseFrequency(dayCallUseFrequency)
                .nightCallUseFrequency(nightCallUseFrequency)
                .dayCallUseDuration(dayCallUseDuration)
                .nightCallUseDuration(nightCallUseDuration)
                .dayLightExposure(dayLightExposure)
                .nightLightExposure(nightLightExposure)
                .dayStepCount(dayStepCount)
                .nightStepCount(nightStepCount)
                .activityScore(activityScore)
                .phoneUsageScore(phoneUsageScore)
                .illuminationExposureScore(illuminationExposureScore)
                .locationDiversityScore(locationDiversityScore)
                .circadianRhythmScore(circadianRhythmScore)
                .dayTimeCount(dayTimeCount)
                .nightTimeCount(nightTimeCount)
                .build();
    }

}

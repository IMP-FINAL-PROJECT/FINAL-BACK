package com.imp.fluffy_mood.entity;

import com.imp.fluffy_mood.dto.AnalysisDto;
import com.imp.fluffy_mood.entity.converter.ListStringConverter;
import com.imp.fluffy_mood.entity.pk.AnalysisPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "daily_life_pattern")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@IdClass(AnalysisPK.class)
public class Analysis {

    @Id
    @Column(name = "id", length = 60, nullable = false)
    private String id; // 사용자 아이디 (e-mail)

    @Column(name = "place_diversity")
    @Convert(converter = ListStringConverter.class)
    private List<List<? extends Number>> placeDiversity;

    @Column(name = "home_stay_percentage")
    private float homeStayPercentage;

    @Column(name = "life_routine_consistency")
    private float lifeRoutineConsistency;

    @Column(name = "day_phone_use_frequency")
    private float dayPhoneUseFrequency;

    @Column(name = "night_phone_use_frequency")
    private float nightPhoneUseFrequency;

    @Column(name = "day_phone_use_duration")
    private float dayPhoneUseDuration;

    @Column(name = "night_phone_use_duration")
    private float nightPhoneUseDuration;

    @Column(name = "day_call_use_frequency")
    private float dayCallUseFrequency;

    @Column(name = "night_call_use_frequency")
    private float nightCallUseFrequency;

    @Column(name = "day_call_use_duration")
    private float dayCallUseDuration;

    @Column(name = "night_call_use_duration")
    private float nightCallUseDuration;

    @Column(name = "day_light_exposure")
    private float dayLightExposure;

    @Column(name = "night_light_exposure")
    private float nightLightExposure;

    @Column(name = "day_step_count")
    private float dayStepCount;

    @Column(name = "night_step_count")
    private float nightStepCount;

    @Column(name = "activity_score")
    private float activityScore;

    @Column(name = "phone_usage_score")
    private float phoneUsageScore;

    @Column(name = "illumination_exposure_score")
    private float illuminationExposureScore;

    @Column(name = "location_diversity_score")
    private float locationDiversityScore;

    @Column(name = "circadian_rhythm_score")
    private float circadianRhythmScore;

    @Id
    @Column(name = "date", columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    public AnalysisDto toDto() {
        return AnalysisDto.builder()
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
                .build();
    }
}

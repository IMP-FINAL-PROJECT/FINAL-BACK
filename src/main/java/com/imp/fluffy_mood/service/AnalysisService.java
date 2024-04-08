package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.AnalysisDto;
import com.imp.fluffy_mood.dto.analysis.DayAnalysisDto;
import com.imp.fluffy_mood.dto.analysis.WeekAnalysisDto;
import com.imp.fluffy_mood.entity.Analysis;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.entity.User;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.AnalysisRepository;
import com.imp.fluffy_mood.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final JpaUserRepository jpaUserRepository;
    private final AnalysisRepository analysisRepository;

    public ResponseEntity<Message> analysis(String id) {

        Message message = new Message();

        User user = jpaUserRepository.findById(id).orElse(null);

        if (user != null) {

            AnalysisDto analysisDto = new AnalysisDto();
            DayAnalysisDto dayAnalysisDto = new DayAnalysisDto();
            WeekAnalysisDto weekAnalysisDto = new WeekAnalysisDto();

            List<Integer> dayIlluminanceList = new ArrayList<>();
            List<Integer> dayScreenDurationList = new ArrayList<>();
            List<Integer> dayScreenFrequencyList = new ArrayList<>();
            List<Integer> dayPedometerList = new ArrayList<>();
            List<Integer> dayPhoneFrequencyList = new ArrayList<>();
            List<Integer> dayPhoneDurationList = new ArrayList<>();

            List<List<? extends Number>> gps = new ArrayList<>();

            List<Integer> weekIlluminanceList = new ArrayList<>();
            List<Integer> weekScreenDurationList = new ArrayList<>();
            List<Integer> weekScreenFrequencyList = new ArrayList<>();
            List<Integer> weekPedometerList = new ArrayList<>();
            List<Integer> weekPhoneFrequencyList = new ArrayList<>();
            List<Integer> weekPhoneDurationList = new ArrayList<>();

            LocalDate lastSunday = LocalDate.now().with(DayOfWeek.SUNDAY).minusWeeks(1);
            LocalDate lastSaturday = lastSunday.plusDays(6);

            System.out.println(lastSunday);
            System.out.println(lastSaturday);

            int dayIlluminanceSum = 0; // 일 조도 합
            int dayScreenDruationSum = 0; // 일 스크린 타임 합
            int dayScreenFrequencySum = 0; // 일 화면 깨우기 합
            int dayPedometerSum = 0; // 일 만보기 합
            int dayPhoneFrequencySum = 0; // 일 전화 빈도 합
            int dayPhoneDurationSum = 0; // 일 전화 시간 합
            int weekIlluminanceSum = 0; // 주 조도 합
            int weekIlluminanceDay = 0; // 주 조도 하루 평균 저장
            int weekScreenDruationSum = 0; // 주 스크린 타임 합
            int weekScreenFrequencySum = 0; // 주 화면 깨우기 합
            int weekPedometerSum = 0; // 주 만보기 합
            int weekPhoneFrequencySum = 0; // 주 전화 빈도 합
            int weekPhoneDurationSum = 0; // 주 전화 시간 합

            analysisDto.setId(user.getId());

            List<Analysis> analysisDay = analysisRepository.findByIdAndTimestamp(id, LocalDate.now());

            for (int i = 0; i < analysisDay.size(); i++) {

                // 조도 평균
                for (int j = 0; j < analysisDay.get(i).getIlluminance().size(); j++) {
                    dayIlluminanceSum += analysisDay.get(i).getIlluminance().get(j);

                    if (j == analysisDay.get(i).getIlluminance().size() - 1) {
                        dayIlluminanceSum /= analysisDay.get(i).getIlluminance().size();
                        dayIlluminanceList.add(dayIlluminanceSum); // 저장
                    }
                }

                // 스크린 타임 List & 총 사용 시간 & 만보기 & 전화 시간 및 빈도
                dayScreenDruationSum += analysisDay.get(i).getScreenDuration();
                dayScreenFrequencySum += analysisDay.get(i).getScreenFrequency();
                dayPedometerSum += analysisDay.get(i).getPedometer();
                dayPhoneFrequencySum += analysisDay.get(i).getPhoneFrequency();
                dayPhoneDurationSum += analysisDay.get(i).getPhoneDuration();
                dayScreenDurationList.add(analysisDay.get(i).getScreenDuration());
                dayScreenFrequencyList.add(analysisDay.get(i).getScreenFrequency());
                dayPedometerList.add(analysisDay.get(i).getPedometer());
                dayPhoneFrequencyList.add(analysisDay.get(i).getPhoneFrequency());
                dayPhoneDurationList.add(analysisDay.get(i).getPhoneDuration());

                // gps 좌표 값
                gps.addAll(analysisDay.get(i).getGps());

            }

            dayAnalysisDto.setIlluminance(dayIlluminanceList);
            dayAnalysisDto.setPedometer(dayPedometerSum);
            dayAnalysisDto.setPedometerList(dayPedometerList);
            dayAnalysisDto.setScreenDuration(dayScreenDruationSum);
            dayAnalysisDto.setScreenDurationList(dayScreenDurationList);
            dayAnalysisDto.setScreenFrequency(dayScreenFrequencySum);
            dayAnalysisDto.setScreenFrequencyList(dayScreenFrequencyList);
            dayAnalysisDto.setPhoneFrequency(dayPhoneFrequencySum);
            dayAnalysisDto.setPhoneFrequencyList(dayPhoneFrequencyList);
            dayAnalysisDto.setPhoneDuration(dayPhoneDurationSum);
            dayAnalysisDto.setPhoneDurationList(dayPhoneDurationList);

            analysisDto.setGps(gps);

            List<Analysis> analysisWeek = analysisRepository.findByIdAndTimestampBetween(id, lastSunday, lastSaturday);

            for(int i = 0; i < analysisWeek.size(); i++) {

                if (analysisWeek.get(i).getTimestamp().equals(lastSunday)) {
                    // 조도 하루씩 계산
                    for (int j = 0; j < analysisWeek.get(i).getIlluminance().size(); j++) {

                        weekIlluminanceSum += analysisWeek.get(i).getIlluminance().get(j);

                        if (j == analysisWeek.get(i).getIlluminance().size() - 1) {
                            weekIlluminanceSum /= analysisWeek.get(i).getIlluminance().size();
                            weekIlluminanceDay += weekIlluminanceSum;
                        }

                    }

                    // 만보기, 스크린 타임, 사용 시간 하루씩 계산
                    weekScreenDruationSum += analysisWeek.get(i).getScreenDuration();
                    weekScreenFrequencySum += analysisWeek.get(i).getScreenFrequency();
                    weekPedometerSum += analysisWeek.get(i).getPedometer();
                    weekPhoneFrequencySum += analysisWeek.get(i).getPhoneFrequency();
                    weekPhoneDurationSum += analysisWeek.get(i).getPhoneDuration();

                    if(i == analysisWeek.size() - 1) {
                        weekIlluminanceList.add(weekIlluminanceSum);
                        weekPedometerList.add(weekPedometerSum);
                        weekScreenDurationList.add(weekScreenDruationSum);
                        weekScreenFrequencyList.add(weekScreenFrequencySum);
                        weekPhoneFrequencyList.add(weekPhoneFrequencySum);
                        weekPhoneDurationList.add(weekPhoneDurationSum);
                    }

                } else {
                    weekIlluminanceList.add(weekIlluminanceSum);
                    weekPedometerList.add(weekPedometerSum);
                    weekScreenDurationList.add(weekScreenDruationSum);
                    weekScreenFrequencyList.add(weekScreenFrequencySum);
                    weekPhoneFrequencyList.add(weekPhoneFrequencySum);
                    weekPhoneDurationList.add(weekPhoneDurationSum);
                    weekIlluminanceSum = 0;
                    weekIlluminanceDay = 0;
                    weekPedometerSum = 0;
                    weekScreenDruationSum = 0;
                    weekScreenFrequencySum = 0;
                    weekPhoneFrequencySum = 0;
                    weekPhoneDurationSum = 0;

                    lastSunday = lastSunday.plusDays(1);

                    i -= 1;
                }

            }

            weekAnalysisDto.setIlluminance(weekIlluminanceList);
            weekAnalysisDto.setPedometerList(weekPedometerList);
            weekAnalysisDto.setScreenDurationList(weekScreenDurationList);
            weekAnalysisDto.setScreenFrequencyList(weekScreenFrequencyList);
            weekAnalysisDto.setPhoneFrequencyList(weekPhoneFrequencyList);
            weekAnalysisDto.setPhoneDurationList(weekPhoneDurationList);

            analysisDto.setDay(dayAnalysisDto);
            analysisDto.setWeek(weekAnalysisDto);

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(analysisDto);

        } else {
            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
            message.setResult(true);
            message.setMessage("no user");
            message.setData(null);
        }

        return ResponseEntity.ok(message);
    }

//    public ResponseEntity<Message> analysisWeek(String id) {
//
//        Message message = new Message();
//
//        User user = jpaUserRepository.findById(id).orElse(null);
//
//        if (user != null) {
//
//            AnalysisDto analysisDto = new AnalysisDto();
//
//            List<Integer> weekIlluminanceList = new ArrayList<>();
//            List<Integer> weekScreenDurationList = new ArrayList<>();
//            List<Integer> weekScreenFrequencyList = new ArrayList<>();
//            List<Integer> weekPedometerList = new ArrayList<>();
//
//            int weekIlluminanceSum = 0; // 주 조도 합
//            int weekIlluminanceDay = 0; // 주 조도 하루 평균 저장
//            int weekScreenDruationSum = 0; // 주 스크린 타임 합
//            int weekScreenFrequencySum = 0; // 주 화면 깨우기 합
//            int weekPedometerSum = 0; // 주 만보기 합
//
//            LocalDate lastMonday = LocalDate.now().with(DayOfWeek.MONDAY).minusWeeks(1);
//            LocalDate lastSunday = lastMonday.plusDays(6);
//
//            analysisDto.setId(user.getId());
//
//            List<Analysis> analysis = analysisRepository.findByIdAndTimestampBetween(id, lastMonday, lastSunday);
//
//            for(int i = 0; i < analysis.size(); i++) {
//
//                if (analysis.get(i).getTimestamp().equals(lastMonday)) {
//                    // 조도 하루씩 계산
//                    for (int j = 0; j < analysis.get(i).getIlluminance().size(); j++) {
//
//                        weekIlluminanceSum += analysis.get(i).getIlluminance().get(j);
//
//                        if (j == analysis.get(i).getIlluminance().size() - 1) {
//                            weekIlluminanceSum /= analysis.get(i).getIlluminance().size();
//                            weekIlluminanceDay += weekIlluminanceSum;
//                        }
//
//                    }
//
//                    // 만보기, 스크린 타임, 사용 시간 하루씩 계산
//                    weekScreenDruationSum += analysis.get(i).getScreenDuration();
//                    weekScreenFrequencySum += analysis.get(i).getScreenFrequency();
//                    weekPedometerSum += analysis.get(i).getPedometer();
//
//                    if(i == analysis.size() - 1) {
//                        weekIlluminanceList.add(weekIlluminanceSum);
//                        weekPedometerList.add(weekPedometerSum);
//                        weekScreenDurationList.add(weekScreenDruationSum);
//                        weekScreenFrequencyList.add(weekScreenFrequencySum);
//                    }
//
//                } else {
//                    weekIlluminanceList.add(weekIlluminanceSum);
//                    weekPedometerList.add(weekPedometerSum);
//                    weekScreenDurationList.add(weekScreenDruationSum);
//                    weekScreenFrequencyList.add(weekScreenFrequencySum);
//                    weekIlluminanceSum = 0;
//                    weekIlluminanceDay = 0;
//                    weekPedometerSum = 0;
//                    weekScreenDruationSum = 0;
//                    weekScreenFrequencySum = 0;
//
//                    lastMonday = lastMonday.plusDays(1);
//
//                    i -= 1;
//                }
//
//            }
//
//            analysisDto.setWeekIlluminance(weekIlluminanceList);
//            analysisDto.setWeekPedometerList(weekPedometerList);
//            analysisDto.setWeekScreenDurationList(weekScreenDurationList);
//            analysisDto.setWeekScreenFrequencyList(weekScreenFrequencyList);
//
//            message.setStatus(StatusEnum.OK.getStatusCode());
//            message.setResult(true);
//            message.setMessage("Success");
//            message.setData(analysisDto);
//
//        } else {
//            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
//            message.setResult(true);
//            message.setMessage("no user");
//            message.setData(null);
//        }
//
//        return ResponseEntity.ok(message);
//    }

}
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
            List<Integer> dayCallFrequencyList = new ArrayList<>();
            List<Integer> dayCallDurationList = new ArrayList<>();

            List<List<? extends Number>> gps = new ArrayList<>();

            List<Integer> weekIlluminanceList = new ArrayList<>();
            List<Integer> weekScreenDurationList = new ArrayList<>();
            List<Integer> weekScreenFrequencyList = new ArrayList<>();
            List<Integer> weekPedometerList = new ArrayList<>();
            List<Integer> weekCallFrequencyList = new ArrayList<>();
            List<Integer> weekCallDurationList = new ArrayList<>();

            LocalDate lastSunday = LocalDate.now().with(DayOfWeek.SUNDAY).minusWeeks(1);
            LocalDate lastSaturday = lastSunday.plusDays(6);

            int dayIlluminanceSum = 0; // 일 조도 합
            int dayScreenDurationSum = 0; // 일 스크린 타임 합
            int dayScreenFrequencySum = 0; // 일 화면 깨우기 합
            int dayPedometerSum = 0; // 일 만보기 합
            int dayCallFrequencySum = 0; // 일 전화 빈도 합
            int dayCallDurationSum = 0; // 일 전화 시간 합
            int weekIlluminanceSum = 0; // 주 조도 합
            int weekIlluminanceDay = 0; // 주 조도 하루 평균 저장
            int weekScreenDurationSum = 0; // 주 스크린 타임 합
            int weekScreenFrequencySum = 0; // 주 화면 깨우기 합
            int weekPedometerSum = 0; // 주 만보기 합
            int weekCallFrequencySum = 0; // 주 전화 빈도 합
            int weekCallDurationSum = 0; // 주 전화 시간 합
            int weekScreenDurationAll = 0; // 주 스크린 타임 총 합
            int weekScreenFrequencyAll = 0; // 주 화면 깨우기 총 합
            int weekPedometerAll = 0; // 주 만보기 총 합
            int weekCallFrequencyAll = 0; // 주 전화 빈도 총 합
            int weekCallDurationAll = 0; // 주 전화 시간 총 합

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
                dayScreenDurationSum += analysisDay.get(i).getScreenDuration();
                dayScreenFrequencySum += analysisDay.get(i).getScreenFrequency();
                dayPedometerSum += analysisDay.get(i).getPedometer();
                dayCallFrequencySum += analysisDay.get(i).getCallFrequency();
                dayCallDurationSum += analysisDay.get(i).getCallDuration();
                dayScreenDurationList.add(analysisDay.get(i).getScreenDuration());
                dayScreenFrequencyList.add(analysisDay.get(i).getScreenFrequency());
                dayPedometerList.add(analysisDay.get(i).getPedometer());
                dayCallFrequencyList.add(analysisDay.get(i).getCallFrequency());
                dayCallDurationList.add(analysisDay.get(i).getCallDuration());

                // gps 좌표 값
                gps.addAll(analysisDay.get(i).getGps());

            }

            dayAnalysisDto.setIlluminance(dayIlluminanceList);
            dayAnalysisDto.setPedometer(dayPedometerSum);
            dayAnalysisDto.setPedometerList(dayPedometerList);
            dayAnalysisDto.setScreenDuration(dayScreenDurationSum);
            dayAnalysisDto.setScreenDurationList(dayScreenDurationList);
            dayAnalysisDto.setScreenFrequency(dayScreenFrequencySum);
            dayAnalysisDto.setScreenFrequencyList(dayScreenFrequencyList);
            dayAnalysisDto.setCallFrequency(dayCallFrequencySum);
            dayAnalysisDto.setCallFrequencyList(dayCallFrequencyList);
            dayAnalysisDto.setCallDuration(dayCallDurationSum);
            dayAnalysisDto.setCallDurationList(dayCallDurationList);

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
                    weekScreenDurationSum += analysisWeek.get(i).getScreenDuration();
                    weekScreenFrequencySum += analysisWeek.get(i).getScreenFrequency();
                    weekPedometerSum += analysisWeek.get(i).getPedometer();
                    weekCallFrequencySum += analysisWeek.get(i).getCallFrequency();
                    weekCallDurationSum += analysisWeek.get(i).getCallDuration();

                    if(i == analysisWeek.size() - 1) {
                        weekIlluminanceList.add(weekIlluminanceSum);
                        weekPedometerList.add(weekPedometerSum);
                        weekScreenDurationList.add(weekScreenDurationSum);
                        weekScreenFrequencyList.add(weekScreenFrequencySum);
                        weekCallFrequencyList.add(weekCallFrequencySum);
                        weekCallDurationList.add(weekCallDurationSum);
                        weekScreenDurationAll += weekScreenDurationSum;
                        weekScreenFrequencyAll += weekScreenFrequencySum;
                        weekPedometerAll += weekPedometerSum;
                        weekCallDurationAll += weekCallDurationSum;
                        weekCallFrequencyAll += weekCallFrequencyAll;
                    }

                } else {
                    weekIlluminanceList.add(weekIlluminanceSum);
                    weekPedometerList.add(weekPedometerSum);
                    weekScreenDurationList.add(weekScreenDurationSum);
                    weekScreenFrequencyList.add(weekScreenFrequencySum);
                    weekCallFrequencyList.add(weekCallFrequencySum);
                    weekCallDurationList.add(weekCallDurationSum);
                    weekScreenDurationAll += weekScreenDurationSum;
                    weekScreenFrequencyAll += weekScreenFrequencySum;
                    weekPedometerAll += weekPedometerSum;
                    weekCallDurationAll += weekCallDurationSum;
                    weekCallFrequencyAll += weekCallFrequencySum;
                    weekIlluminanceSum = 0;
                    weekIlluminanceDay = 0;
                    weekPedometerSum = 0;
                    weekScreenDurationSum = 0;
                    weekScreenFrequencySum = 0;
                    weekCallFrequencySum = 0;
                    weekCallDurationSum = 0;

                    lastSunday = lastSunday.plusDays(1);

                    i -= 1;
                }

            }

            weekAnalysisDto.setIlluminance(weekIlluminanceList);
            weekAnalysisDto.setPedometerList(weekPedometerList);
            weekAnalysisDto.setScreenDurationList(weekScreenDurationList);
            weekAnalysisDto.setScreenFrequencyList(weekScreenFrequencyList);
            weekAnalysisDto.setCallFrequencyList(weekCallFrequencyList);
            weekAnalysisDto.setCallDurationList(weekCallDurationList);
            weekAnalysisDto.setPedometer(weekPedometerAll);
            weekAnalysisDto.setScreenDuration(weekScreenDurationAll);
            weekAnalysisDto.setScreenFrequency(weekScreenFrequencyAll);
            weekAnalysisDto.setCallDuration(weekCallDurationAll);
            weekAnalysisDto.setCallFrequency(weekCallFrequencyAll);

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
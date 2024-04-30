package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.LogDto;
import com.imp.fluffy_mood.dto.analysis.DayLogDto;
import com.imp.fluffy_mood.dto.analysis.WeekLogDto;
import com.imp.fluffy_mood.entity.Log;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.entity.User;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.LogRepository;
import com.imp.fluffy_mood.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final JpaUserRepository jpaUserRepository;
    private final LogRepository logRepository;

    public ResponseEntity<Message> log(String id, LocalDate date) {

        Message message = new Message();

        User user = jpaUserRepository.findById(id).orElse(null);

        if (user != null) {

            LogDto logDto = new LogDto();
            DayLogDto dayLogDto = new DayLogDto();
            WeekLogDto weekLogDto = new WeekLogDto();

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

            LocalDate lastWeekStart = date.minusWeeks(1).plusDays(1);

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

            logDto.setId(user.getId());

            List<Log> logDay = logRepository.findByIdAndTimestamp(id, date);

            for (int i = 0; i < logDay.size(); i++) {

                // 조도 평균
                for (int j = 0; j < logDay.get(i).getIlluminance().size(); j++) {
                    dayIlluminanceSum += logDay.get(i).getIlluminance().get(j);

                    if (j == logDay.get(i).getIlluminance().size() - 1) {
                        dayIlluminanceSum /= logDay.get(i).getIlluminance().size();
                        dayIlluminanceList.add(dayIlluminanceSum); // 저장
                    }
                }

                // 스크린 타임 List & 총 사용 시간 & 만보기 & 전화 시간 및 빈도
                dayScreenDurationSum += logDay.get(i).getScreenDuration();
                dayScreenFrequencySum += logDay.get(i).getScreenFrequency();
                dayPedometerSum += logDay.get(i).getPedometer();
                dayCallFrequencySum += logDay.get(i).getCallFrequency();
                dayCallDurationSum += logDay.get(i).getCallDuration();
                dayScreenDurationList.add(logDay.get(i).getScreenDuration());
                dayScreenFrequencyList.add(logDay.get(i).getScreenFrequency());
                dayPedometerList.add(logDay.get(i).getPedometer());
                dayCallFrequencyList.add(logDay.get(i).getCallFrequency());
                dayCallDurationList.add(logDay.get(i).getCallDuration());

                // gps 좌표 값
                gps.addAll(logDay.get(i).getGps());

            }

            dayLogDto.setIlluminance(dayIlluminanceList);
            dayLogDto.setPedometer(dayPedometerSum);
            dayLogDto.setPedometerList(dayPedometerList);
            dayLogDto.setScreenDuration(dayScreenDurationSum);
            dayLogDto.setScreenDurationList(dayScreenDurationList);
            dayLogDto.setScreenFrequency(dayScreenFrequencySum);
            dayLogDto.setScreenFrequencyList(dayScreenFrequencyList);
            dayLogDto.setCallFrequency(dayCallFrequencySum);
            dayLogDto.setCallFrequencyList(dayCallFrequencyList);
            dayLogDto.setCallDuration(dayCallDurationSum);
            dayLogDto.setCallDurationList(dayCallDurationList);

            logDto.setGps(gps);

            List<Log> logWeek = logRepository.findByIdAndTimestampBetween(id, lastWeekStart, date);

            for(int i = 0; i < logWeek.size(); i++) {

                if (logWeek.get(i).getTimestamp().equals(lastWeekStart)) {
                    // 조도 하루씩 계산
                    for (int j = 0; j < logWeek.get(i).getIlluminance().size(); j++) {

                        weekIlluminanceSum += logWeek.get(i).getIlluminance().get(j);

                        if (j == logWeek.get(i).getIlluminance().size() - 1) {
                            weekIlluminanceSum /= logWeek.get(i).getIlluminance().size();
                            weekIlluminanceDay += weekIlluminanceSum;
                        }

                    }

                    // 만보기, 스크린 타임, 사용 시간 하루씩 계산
                    weekScreenDurationSum += logWeek.get(i).getScreenDuration();
                    weekScreenFrequencySum += logWeek.get(i).getScreenFrequency();
                    weekPedometerSum += logWeek.get(i).getPedometer();
                    weekCallFrequencySum += logWeek.get(i).getCallFrequency();
                    weekCallDurationSum += logWeek.get(i).getCallDuration();

                    if(i == logWeek.size() - 1) {
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

                    lastWeekStart = lastWeekStart.plusDays(1);

                    i -= 1;
                }

            }

            weekLogDto.setIlluminance(weekIlluminanceList);
            weekLogDto.setPedometerList(weekPedometerList);
            weekLogDto.setScreenDurationList(weekScreenDurationList);
            weekLogDto.setScreenFrequencyList(weekScreenFrequencyList);
            weekLogDto.setCallFrequencyList(weekCallFrequencyList);
            weekLogDto.setCallDurationList(weekCallDurationList);
            weekLogDto.setPedometer((!weekPedometerList.isEmpty()) ? weekPedometerAll / weekPedometerList.size() : weekPedometerAll);
            weekLogDto.setScreenDuration((!weekScreenDurationList.isEmpty()) ? weekScreenDurationAll / weekScreenDurationList.size() : weekScreenDurationAll);
            weekLogDto.setScreenFrequency((!weekScreenFrequencyList.isEmpty()) ? weekScreenFrequencyAll / weekScreenFrequencyList.size() : weekScreenFrequencyAll);
            weekLogDto.setCallDuration((!weekCallDurationList.isEmpty()) ? weekCallDurationAll / weekCallDurationList.size() : weekCallDurationAll);
            weekLogDto.setCallFrequency((!weekCallFrequencyList.isEmpty()) ? weekCallFrequencyAll / weekCallFrequencyList.size() : weekCallFrequencyAll);

            logDto.setDay(dayLogDto);
            logDto.setWeek(weekLogDto);

            logDto.setTimestamp(date);

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(logDto);

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
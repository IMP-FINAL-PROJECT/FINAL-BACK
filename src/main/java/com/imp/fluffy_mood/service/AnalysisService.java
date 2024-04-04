package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.AnalysisDto;
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

    public ResponseEntity<Message> analysisOne(String id) {

        Message message = new Message();

        User user = jpaUserRepository.findById(id).orElse(null);

        if (user != null) {

            AnalysisDto analysisDto = new AnalysisDto();

            List<Integer> illuminanceList = new ArrayList<>();
            List<Integer> screenDurationList = new ArrayList<>();
            List<Integer> screenFrequencyList = new ArrayList<>();
            List<Integer> pedometerList = new ArrayList<>();
            List<List<? extends Number>> gps = new ArrayList<>();

            int illuminanceSum = 0; // 조도 합
            int screenDruationSum = 0; // 스크린 타임 합
            int screenFrequencySum = 0; // 화면 깨우기 합
            int pedometerSum = 0; // 만보기 합

            analysisDto.setId(user.getId());

            List<Analysis> analysis = analysisRepository.findByIdAndTimestamp(id, LocalDate.now().minusDays(1));

            for (int i = 0; i < analysis.size(); i++) {

                // 조도 평균
                for (int j = 0; j < analysis.get(i).getIlluminance().size(); j++) {
                    illuminanceSum += analysis.get(i).getIlluminance().get(j);

                    if (j == analysis.get(i).getIlluminance().size() - 1) {
                        illuminanceSum /= analysis.get(i).getIlluminance().size();
                        illuminanceList.add(illuminanceSum); // 저장
                    }
                }

                // 스크린 타임 List & 총 사용 시간 & 만보기
                screenDruationSum += analysis.get(i).getScreenDuration();
                screenFrequencySum += analysis.get(i).getScreenFrequency();
                pedometerSum += analysis.get(i).getPedometer();
                screenDurationList.add(analysis.get(i).getScreenDuration());
                screenFrequencyList.add(analysis.get(i).getScreenFrequency());
                pedometerList.add(analysis.get(i).getPedometer());

                // gps 좌표 값
                gps.addAll(analysis.get(i).getGps());

            }

            analysisDto.setIlluminance(illuminanceList);
            analysisDto.setPedometer(pedometerSum);
            analysisDto.setPedometerList(pedometerList);
            analysisDto.setScreenDuration(screenDruationSum);
            analysisDto.setScreenDurationList(screenDurationList);
            analysisDto.setScreenFrequency(screenFrequencySum);
            analysisDto.setScreenFrequencyList(screenFrequencyList);
            analysisDto.setGps(gps);

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

    public ResponseEntity<Message> analysisWeek(String id) {

        Message message = new Message();

        User user = jpaUserRepository.findById(id).orElse(null);

        if (user != null) {

            AnalysisDto analysisDto = new AnalysisDto();

            List<Integer> illuminanceList = new ArrayList<>();
            List<Integer> screenDurationList = new ArrayList<>();
            List<Integer> screenFrequencyList = new ArrayList<>();
            List<Integer> pedometerList = new ArrayList<>();

            int illuminanceSum = 0; // 조도 합
            int illuminanceDay = 0; // 조도 하루 평균 저장
            int screenDruationSum = 0; // 스크린 타임 합
            int screenFrequencySum = 0; // 화면 깨우기 합
            int pedometerSum = 0; // 만보기 합

            LocalDate lastMonday = LocalDate.now().with(DayOfWeek.MONDAY).minusWeeks(1);
            LocalDate lastSunday = lastMonday.plusDays(6);

            analysisDto.setId(user.getId());

            List<Analysis> analysis = analysisRepository.findByIdAndTimestampBetween(id, lastMonday, lastSunday);

            for(int i = 0; i < analysis.size(); i++) {

                if (analysis.get(i).getTimestamp().equals(lastMonday)) {
                    // 조도 하루씩 계산
                    for (int j = 0; j < analysis.get(i).getIlluminance().size(); j++) {

                        illuminanceSum += analysis.get(i).getIlluminance().get(j);

                        if (j == analysis.get(i).getIlluminance().size() - 1) {
                            illuminanceSum /= analysis.get(i).getIlluminance().size();
                            illuminanceDay += illuminanceSum;
                        }

                    }

                    // 만보기, 스크린 타임, 사용 시간 하루씩 계산
                    screenDruationSum += analysis.get(i).getScreenDuration();
                    screenFrequencySum += analysis.get(i).getScreenFrequency();
                    pedometerSum += analysis.get(i).getPedometer();

                    if(i == analysis.size() - 1) {
                        illuminanceList.add(illuminanceSum);
                        pedometerList.add(pedometerSum);
                        screenDurationList.add(screenDruationSum);
                        screenFrequencyList.add(screenFrequencySum);
                    }

                } else {
                    illuminanceList.add(illuminanceSum);
                    pedometerList.add(pedometerSum);
                    screenDurationList.add(screenDruationSum);
                    screenFrequencyList.add(screenFrequencySum);
                    illuminanceSum = 0;
                    illuminanceDay = 0;
                    pedometerSum = 0;
                    screenDruationSum = 0;
                    screenFrequencySum = 0;

                    lastMonday = lastMonday.plusDays(1);

                    i -= 1;
                }

            }

            analysisDto.setIlluminance(illuminanceList);
            analysisDto.setPedometerList(pedometerList);
            analysisDto.setScreenDurationList(screenDurationList);
            analysisDto.setScreenFrequencyList(screenFrequencyList);

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

}
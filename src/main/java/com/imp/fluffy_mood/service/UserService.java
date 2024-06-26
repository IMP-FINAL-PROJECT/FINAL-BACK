package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.HomeDto;
import com.imp.fluffy_mood.dto.UserDto;
import com.imp.fluffy_mood.entity.*;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.HappinessRepository;
import com.imp.fluffy_mood.repository.JpaUserRepository;

import com.imp.fluffy_mood.repository.mapping.HappinessMapping;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JpaUserRepository jpaUserRepository;
    private final HappinessRepository happinessRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 로그인
    public ResponseEntity<Message> login(UserDto userDto) {

        Message message = new Message();

        User user = jpaUserRepository.findById(userDto.getId()).orElse(null);

        // 로그인 성공
        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

            user.updateLastLogin();

            if (userDto.getToken() != null && user.getToken() != userDto.getToken()) {
                user.updateToken(userDto.getToken());
            }

            jpaUserRepository.save(user);

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(user);
        }

        // 사용자 존재 X
        else if (user == null) {
            message.setStatus(StatusEnum.UNAUTHORIZED.getStatusCode());
            message.setResult(false);
            message.setMessage("No User");
            message.setData(null);
        }
        // ID or Password 불일치
        else {
            message.setStatus(StatusEnum.UNAUTHORIZED.getStatusCode());
            message.setResult(true);
            message.setMessage("ID or password does not match");
            message.setData(null);
        }

        return ResponseEntity.ok(message);

    }

    // 아이디 중복 검사
    public ResponseEntity<Message> idValidation(String id) {

        Message message = new Message();

        User user = jpaUserRepository.findById(id).orElse(null);

        if (user == null) {
            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
        } else {
            message.setStatus(StatusEnum.CONFLICT.getStatusCode());
            message.setResult(true);
            message.setMessage("Duplicate ID.");
        }

        return ResponseEntity.ok(message);

    }

    // 회원가입
    public ResponseEntity<Message> register(UserDto userDto) {

        Message message = new Message();

        User user = jpaUserRepository.findById(userDto.getId()).orElse(null);

        // DB에 사용자가 없으면 회원가입 성공
        if (user == null) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User newUser = userDto.toEntity();
            jpaUserRepository.save(newUser);

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(newUser);
        }
        // 회원가입 실패 시
        else {
            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
            message.setResult(true);
            message.setMessage("Cannot Register");
            message.setData(null);
        }

        return ResponseEntity.ok(message);

    }

    // 사용자 정보 변경
    public ResponseEntity<Message> changeInfo(String id, UserDto userDto) {

        Message message = new Message();
        User user = jpaUserRepository.findById(id).orElse(null);

        if (user != null) {


            user.update(userDto);

            jpaUserRepository.save(user);

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(user);

        } else {
            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
            message.setResult(true);
            message.setMessage("Cannot Change");
            message.setData(null);
        }

        return ResponseEntity.ok(message);

    }

    // 사용자 정보 조회
    public ResponseEntity<Message> information(String id) {

        Message message = new Message();

        User user = jpaUserRepository.findById(id).orElse(null);

        if (user != null) {

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(user);
        } else {

            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
            message.setResult(true);
            message.setMessage("Can't get Data");
            message.setData(null);

        }

        return ResponseEntity.ok(message);
    }

    public ResponseEntity<Message> home(String id) {

        Message message = new Message();

        User user = jpaUserRepository.findById(id).orElse(null);

        if (user != null) {

            LocalDate today = LocalDate.now();
            LocalDate lastWeekStart = today.minusWeeks(1).plusDays(1);

            List<HappinessMapping> happinessWeek = happinessRepository.findByIdAndTimestampBetween(id, lastWeekStart, today);
            List<Integer> pointList = new ArrayList<>();

            HomeDto homeDto = new HomeDto();

            for (int i = 0; i < happinessWeek.size(); i++) {

                if(happinessWeek.get(i).getTimestamp().equals(lastWeekStart)) {
                    pointList.add(happinessWeek.get(i).getPoint());
                } else {
                    pointList.add(0);
                    i -= 1;
                }

                lastWeekStart = lastWeekStart.plusDays(1);
            }

            while(pointList.size() != 7) {
                pointList.add(0);
            }

            homeDto.setId(id);
            homeDto.setPointList(pointList);
            homeDto.setPoint(pointList.get(pointList.size() - 1));

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");
            message.setData(homeDto);

        } else {

            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
            message.setResult(true);
            message.setMessage("No data");

        }

        return ResponseEntity.ok(message);

    }

}
package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.UserDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.entity.User;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JpaUserRepository jpaUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 로그인
    public ResponseEntity<Message> login(UserDto userDto) {

            Message message = new Message();

            User user = jpaUserRepository.findById(userDto.getId()).orElse(null);

            // 로그인 성공
            if (user != null && userDto.getPassword().equals(user.getPassword())) {
                message.setStatus(StatusEnum.OK.getStatusCode());
                message.setResult(true);
                message.setMessage("Success");
                message.setData(user);
            }
            // 사용자 존재 X
            else if(user == null){
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
    public ResponseEntity<?> idValidation(String id) {

            Message message = new Message();

            User user = jpaUserRepository.findById(id).orElse(null);

            if(user == null) {
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
    public ResponseEntity<?> register(UserDto userDto) {

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

}
package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.MoodDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.MoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoodService {

    private final MoodRepository moodRepository;

    public ResponseEntity<Message> insert(MoodDto moodDto) {

        Message message = new Message();

        if(moodDto != null) {

            moodRepository.save(moodDto.toEntity());

            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setResult(true);
            message.setMessage("Success");

        } else {

            message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
            message.setResult(false);
            message.setMessage("Can't save mood score.");

        }

        return ResponseEntity.ok(message);

    }

}

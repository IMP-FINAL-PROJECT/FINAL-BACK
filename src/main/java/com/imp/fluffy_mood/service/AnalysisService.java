package com.imp.fluffy_mood.service;

import com.imp.fluffy_mood.dto.AnalysisDto;
import com.imp.fluffy_mood.entity.Analysis;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.entity.User;
import com.imp.fluffy_mood.enums.StatusEnum;
import com.imp.fluffy_mood.repository.AnalysisRepository;
import com.imp.fluffy_mood.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final JpaUserRepository jpaUserRepository;

    public ResponseEntity<Message> analysis(String id, LocalDate date) {

        Message message = new Message();

        User user = jpaUserRepository.findById(id).orElse(null);

        if(user != null) {

            Analysis analysis = analysisRepository.findByIdAndDate(id, date);

            if(analysis == null) {
                message.setStatus(StatusEnum.BAD_REQUEST.getStatusCode());
                message.setResult(false);
                message.setMessage("No Data");
                message.setData(null);

                return ResponseEntity.ok(message);
            }

            AnalysisDto analysisDto = analysis.toDto();

            analysisDto.setDate(date);

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

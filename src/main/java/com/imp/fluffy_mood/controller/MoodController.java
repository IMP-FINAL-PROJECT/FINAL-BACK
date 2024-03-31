package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.dto.MoodDto;
import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.MoodService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/mood")
public class MoodController {

    private final MoodService moodService;

    public MoodController(MoodService moodService) {
        this.moodService = moodService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Message> insert(HttpServletRequest request, @RequestBody MoodDto moodDto) {
        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, score : {}", moodDto.getId(), moodDto.getScore());

        ResponseEntity<Message> response = moodService.insert(moodDto);

        log.debug("Data : {}", response.getBody());

        return response;
    }

}

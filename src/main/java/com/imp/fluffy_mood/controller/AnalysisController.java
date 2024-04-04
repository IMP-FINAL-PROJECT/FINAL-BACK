package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.AnalysisService;
import com.imp.fluffy_mood.service.MoodService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }


    @GetMapping("/one")
    public ResponseEntity<Message> analysisOne(HttpServletRequest request, @RequestParam String id) {

        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, Method : analysisOne", id);

        ResponseEntity<Message> response = analysisService.analysisOne(id);

        log.debug("Data : {}", response.getBody());

        return response;

    }

    @GetMapping("/week")
    public ResponseEntity<Message> analysisWeek(HttpServletRequest request, @RequestParam String id) {

        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, Method : analysisWeek", id);

        ResponseEntity<Message> response = analysisService.analysisWeek(id);

        log.debug("Data : {}", response.getBody());

        return response;

    }
}

package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.AnalysisService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) { this.analysisService = analysisService; }

    @GetMapping("")
    public ResponseEntity<Message> analysis(HttpServletRequest request, @RequestParam String id, @RequestParam LocalDate date) {

        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, date : {}, Method : analysis", id, date);

        ResponseEntity<Message> response = analysisService.analysis(id, date);

        log.debug("Data : {}", response.getBody());

        return response;

    }
}

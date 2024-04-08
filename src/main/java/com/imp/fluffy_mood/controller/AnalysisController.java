package com.imp.fluffy_mood.controller;

import com.imp.fluffy_mood.entity.Message;
import com.imp.fluffy_mood.service.AnalysisService;
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


    @GetMapping("")
    public ResponseEntity<Message> analysis(HttpServletRequest request, @RequestParam String id) {

        log.debug("Accessed IP : {}", request.getRemoteAddr());
        log.debug("id : {}, Method : analysis", id);

        ResponseEntity<Message> response = analysisService.analysis(id);

        log.debug("Data : {}", response.getBody());

        return response;

    }

//    @GetMapping("/week")
//    public ResponseEntity<Message> analysisWeek(HttpServletRequest request, @RequestParam String id) {
//
//        log.debug("Accessed IP : {}", request.getRemoteAddr());
//        log.debug("id : {}, Method : analysisWeek", id);
//
//        ResponseEntity<Message> response = analysisService.analysisWeek(id);
//
//        log.debug("Data : {}", response.getBody());
//
//        return response;
//
//    }
}
